package com.kevin.live.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kevin.live.R;
import com.kevin.live.activity.TrainInfoByNumberActivity;
import com.kevin.live.adapter.TrainNoHistoryAdapter;
import com.kevin.live.adapter.TrainNoHistoryAllAdapter;
import com.kevin.live.base.BaseFragment;
import com.kevin.live.database.DBManager;
import com.kevin.live.database.TrainNoHistoryEntity;
import com.kevin.live.util.DateUtils;
import com.kevin.live.util.SPUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Kevin on 2018/8/30<br/>
 * Blog:http://student9128.top/<br/>
 * Describe:<br/> query train info by train number.
 */
public class TrainInfoByTrainNoFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.tv_query_train_station)
    TextView tvQueryTrainStation;
    @BindView(R.id.tv_train_number)
    EditText etTrainNumber;
    @BindView(R.id.ll_container_station)
    LinearLayout llContainerStation;
    @BindView(R.id.btn_query)
    Button btnQuery;
    @BindView(R.id.tv_show_or_clear_history)
    TextView tvShowOrClearHistory;
    @BindView(R.id.tv_clear_history)
    TextView tvClearHistory;
    @BindView(R.id.rl_recycler_view_history)
    RecyclerView rlRecyclerViewHistory;
    @BindView(R.id.rl_history)
    RelativeLayout rlHistory;
    @BindView(R.id.rl_recycler_view_all_history)
    RecyclerView rlRecyclerViewAllHistory;

    private DBManager dbManager;

    private TrainNoHistoryAdapter trainHistoryAdapter;
    private TrainNoHistoryAllAdapter trainNoHistoryAllAdapter;
    private List<TrainNoHistoryEntity> data = new ArrayList<>();
    private List<TrainNoHistoryEntity> allData = new ArrayList<>();

    private boolean isShowMoreNumber = false;

    private OnStationQueryClickListener listener;


    public interface OnStationQueryClickListener {
        public void onStationQueryClick();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnStationQueryClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnStationQueryClickListener");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SPUtil.setSP("isShowMoreNumber",mActivity,false);
    }
    @Override
    public int setLayoutResId() {
        return R.layout.fragment_train_info_by_train_number;
    }

    @Override
    public void initView() {
        dbManager = DBManager.getInstance();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        rlRecyclerViewHistory.setLayoutManager(linearLayoutManager);
        trainHistoryAdapter = new TrainNoHistoryAdapter(mActivity, data);
        rlRecyclerViewHistory.setAdapter(trainHistoryAdapter);

        //all history
        LinearLayoutManager linearLayoutManagerAll = new LinearLayoutManager(mActivity);
        rlRecyclerViewAllHistory.setLayoutManager(linearLayoutManagerAll);
        trainNoHistoryAllAdapter = new TrainNoHistoryAllAdapter(mActivity, allData, true);
        rlRecyclerViewAllHistory.setAdapter(trainNoHistoryAllAdapter);

        tvShowOrClearHistory.setVisibility(View.GONE);

        isShowMoreNumber = SPUtil.getBooleanSP("isShowMoreNumber", mActivity);
    }

    @Override
    public void initData() {
        List<TrainNoHistoryEntity> trainNoHistoryEntities = dbManager.retrieveAllNoHistory();
        trainHistoryAdapter.updateData(trainNoHistoryEntities);
        trainNoHistoryAllAdapter.updateData(trainNoHistoryEntities);
        if (trainNoHistoryEntities != null && trainNoHistoryEntities.size() > 0) {
            tvShowOrClearHistory.setVisibility(View.VISIBLE);
            if (trainNoHistoryEntities.size() > 5) {
                tvShowOrClearHistory.setText("显示更多");
            } else {
                tvShowOrClearHistory.setText("清除历史");
            }
        }
        rlRecyclerViewAllHistory.setVisibility(View.GONE);

        if (isShowMoreNumber) {
            rlRecyclerViewAllHistory.setVisibility(View.VISIBLE);
            tvShowOrClearHistory.setVisibility(View.VISIBLE);
            tvClearHistory.setVisibility(View.VISIBLE);
            rlRecyclerViewHistory.setVisibility(View.GONE);
        } else {
            rlRecyclerViewAllHistory.setVisibility(View.GONE);
            tvClearHistory.setVisibility(View.GONE);
            rlRecyclerViewHistory.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initListener() {
        btnQuery.setOnClickListener(this);
        tvQueryTrainStation.setOnClickListener(this);
        tvClearHistory.setOnClickListener(this);
        tvQueryTrainStation.setOnClickListener(this);
        tvShowOrClearHistory.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_query:
                SPUtil.setSP("isShowMoreNumber",mActivity, isShowMoreNumber);

                String trainNumber = etTrainNumber.getText().toString().trim();
                if (TextUtils.isEmpty(trainNumber)) {
                    showToast("请输入车次");
                    return;
                }
                String currentTime = DateUtils.getCurrentTime();

                dbManager.insertNoHistory(trainNumber, currentTime);

                TrainNoHistoryEntity trainNoHistoryEntity = new TrainNoHistoryEntity();
                trainNoHistoryEntity.setTrainNo(trainNumber);
                trainNoHistoryEntity.setTime(currentTime);
                trainHistoryAdapter.insertHistory(trainNoHistoryEntity);
                trainNoHistoryAllAdapter.insertHistory(trainNoHistoryEntity);
                tvShowOrClearHistory.setVisibility(View.VISIBLE);
                if (!isShowMoreNumber) {
                    rlRecyclerViewHistory.setVisibility(View.VISIBLE);
                    rlRecyclerViewAllHistory.setVisibility(View.GONE);
                    tvClearHistory.setVisibility(View.GONE);
                    ((LinearLayoutManager) rlRecyclerViewHistory.getLayoutManager()).scrollToPositionWithOffset(0, 0);
                    trainHistoryAdapter.notifyItemChanged(0);
                } else {
                    rlRecyclerViewAllHistory.setVisibility(View.VISIBLE);
                    rlRecyclerViewHistory.setVisibility(View.GONE);
                    tvClearHistory.setVisibility(View.VISIBLE);
                    ((LinearLayoutManager) rlRecyclerViewAllHistory.getLayoutManager()).scrollToPositionWithOffset(0, 0);
                    trainNoHistoryAllAdapter.notifyItemChanged(0);
                }
                if (data.size() > 5) {
                    if (!"隐藏更多".equals(tvShowOrClearHistory.getText().toString())) {
                        tvShowOrClearHistory.setText("显示更多");
                    }
                } else {
                    tvShowOrClearHistory.setText("清除历史");
                }
                Intent intent = new Intent(mActivity, TrainInfoByNumberActivity.class);
                intent.putExtra("trainNumber",trainNumber);
                startActivity(intent);
                break;
            case R.id.tv_query_train_station:
                listener.onStationQueryClick();
                SPUtil.setSP("isShowMoreNumber",mActivity, isShowMoreNumber);
                break;
            case R.id.tv_show_or_clear_history:
                String showOrClear = tvShowOrClearHistory.getText().toString().trim();
                if ("清除历史".equals(showOrClear)) {
                    clearHistory();
                    tvShowOrClearHistory.setVisibility(View.GONE);
                } else if ("显示更多".equals(showOrClear)) {
                    rlRecyclerViewHistory.setVisibility(View.GONE);
                    rlRecyclerViewAllHistory.setVisibility(View.VISIBLE);
                    tvClearHistory.setVisibility(View.VISIBLE);
                    tvShowOrClearHistory.setText("隐藏更多");
                    isShowMoreNumber = true;
                } else if ("隐藏更多".equals(showOrClear)) {
                    rlRecyclerViewHistory.setVisibility(View.VISIBLE);
                    rlRecyclerViewAllHistory.setVisibility(View.GONE);
                    tvClearHistory.setVisibility(View.GONE);
                    tvShowOrClearHistory.setText("显示更多");
                    isShowMoreNumber = false;
                }
                break;
            case R.id.tv_clear_history:
                clearHistory();
                tvClearHistory.setVisibility(View.GONE);
                tvShowOrClearHistory.setVisibility(View.GONE);
                break;
        }
    }

    private void clearHistory() {
        dbManager.clearHistory();
        List<TrainNoHistoryEntity> trainNoHistoryEntities = dbManager.retrieveAllNoHistory();
        trainHistoryAdapter.updateData(trainNoHistoryEntities);
        trainNoHistoryAllAdapter.updateData(trainNoHistoryEntities);
        if (trainNoHistoryEntities != null && trainNoHistoryEntities.size() > 0) {
            if (trainNoHistoryEntities.size() > 5) {
                tvShowOrClearHistory.setText("显示更多");
            } else {
                tvShowOrClearHistory.setText("清除历史");
            }
        }
        rlRecyclerViewAllHistory.setVisibility(View.GONE);
        isShowMoreNumber = false;
    }

}
