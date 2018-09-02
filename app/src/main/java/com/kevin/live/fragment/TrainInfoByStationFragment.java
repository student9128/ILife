package com.kevin.live.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kevin.live.R;
import com.kevin.live.activity.TrainInfoByStationActivity;
import com.kevin.live.adapter.TrainHistoryAdapter;
import com.kevin.live.adapter.TrainHistoryAllAdapter;
import com.kevin.live.base.BaseFragment;
import com.kevin.live.database.DBManager;
import com.kevin.live.database.TrainHistoryEntity;
import com.kevin.live.util.DateUtils;
import com.kevin.live.util.FontManager;
import com.kevin.live.util.SPUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Kevin on 2018/8/30<br/>
 * Blog:http://student9128.top/<br/>
 * Describe:<br/>query train info by station.
 */
public class TrainInfoByStationFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.tv_query_train_number)
    TextView tvQueryTrainNumber;
    @BindView(R.id.tv_station_start)
    EditText etStationStart;
    @BindView(R.id.tv_exchange)
    TextView tvExchange;
    @BindView(R.id.tv_station_end)
    EditText etStationEnd;
    @BindView(R.id.ll_container_station)
    LinearLayout llContainerStation;
    @BindView(R.id.btn_query)
    Button btnQuery;
    @BindView(R.id.tv_show_or_clear_history)
    TextView tvShowOrClearHistory;
    @BindView(R.id.rl_recycler_view_history)
    RecyclerView rlRecyclerViewHistory;
    @BindView(R.id.rl_history)
    RelativeLayout rlHistory;
    @BindView(R.id.rl_recycler_view_all_history)
    RecyclerView rlRecyclerViewAllHistory;
    @BindView(R.id.tv_clear_history)
    TextView tvClearHistory;

    private DBManager dbManager;

    private TrainHistoryAdapter trainHistoryAdapter;
    private List<TrainHistoryEntity> data = new ArrayList<>();
    private List<TrainHistoryEntity> allData = new ArrayList<>();
    private TrainHistoryAllAdapter trainAllHistoryAdapter;
    /**
     * if show more history.
     * if not, set rlRecyclerViewHistory visible,
     * otherwise,set rlRecyclerViewHistory gone.
     */
    private boolean isShowMore = false;

    private OnTrainNoQueryClickListener listener;

    public interface OnTrainNoQueryClickListener {
        public void onTrainNoQueryClick();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnTrainNoQueryClickListener) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnTrainNoQueryClickListener");
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        SPUtil.setSP("isShowMore",mActivity,false);
    }

    @Override
    public int setLayoutResId() {

        return R.layout.fragment_train_info_by_station;
    }

    @Override
    public void initView() {
        FontManager.fontIcon(llContainerStation, mActivity);
        dbManager = DBManager.getInstance();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false);
        rlRecyclerViewHistory.setLayoutManager(linearLayoutManager);
        trainHistoryAdapter = new TrainHistoryAdapter(mActivity, data);
        rlRecyclerViewHistory.setAdapter(trainHistoryAdapter);

        //all history
        LinearLayoutManager linearLayoutManagerAll = new LinearLayoutManager(mActivity);
        rlRecyclerViewAllHistory.setLayoutManager(linearLayoutManagerAll);
        trainAllHistoryAdapter = new TrainHistoryAllAdapter(mActivity, allData, true);
        rlRecyclerViewAllHistory.setAdapter(trainAllHistoryAdapter);

        tvShowOrClearHistory.setVisibility(View.GONE);

        isShowMore = SPUtil.getBooleanSP("isShowMore", mActivity);


    }

    @Override
    public void initData() {
        List<TrainHistoryEntity> trainHistoryEntities = dbManager.retrieveAllHistory();
        trainHistoryAdapter.updateData(trainHistoryEntities);
        trainAllHistoryAdapter.updateData(trainHistoryEntities);
        if (trainHistoryEntities != null && trainHistoryEntities.size() > 0) {
            tvShowOrClearHistory.setVisibility(View.VISIBLE);
            if (trainHistoryEntities.size() > 5) {
                tvShowOrClearHistory.setText("显示更多");
            } else {
                tvShowOrClearHistory.setText("清除历史");
            }
        }
        rlRecyclerViewAllHistory.setVisibility(View.GONE);

        if (isShowMore) {
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
        tvQueryTrainNumber.setOnClickListener(this);
        tvShowOrClearHistory.setOnClickListener(this);
        tvClearHistory.setOnClickListener(this);
        tvQueryTrainNumber.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_query:
                SPUtil.setSP("isShowMore",mActivity,isShowMore);

                String stationStart = etStationStart.getText().toString().trim();
                String stationEnd = etStationEnd.getText().toString().trim();
                String currentTime = DateUtils.getCurrentTime();
                if (!TextUtils.isEmpty(stationStart) && !TextUtils.isEmpty(stationEnd)) {
                if (stationStart.equals(stationEnd)) {
                    showToast("出发站和终点站一样");
                    return;
                }
                }
                if (TextUtils.isEmpty(stationStart)) {
                    showToast("请输入出发站");
                    return;
                }
                if (TextUtils.isEmpty(stationEnd)) {
                    showToast("请输入终点站");
                    return;
                }

                dbManager.insertHistory(stationStart, stationEnd, currentTime);

                TrainHistoryEntity trainHistoryEntity = new TrainHistoryEntity();
                trainHistoryEntity.setStartStation(stationStart);
                trainHistoryEntity.setEndStation(stationEnd);
                trainHistoryEntity.setTime(currentTime);
                trainHistoryAdapter.insertHistory(trainHistoryEntity);
                trainAllHistoryAdapter.insertHistory(trainHistoryEntity);
                tvShowOrClearHistory.setVisibility(View.VISIBLE);
                if (!isShowMore) {
                    rlRecyclerViewHistory.setVisibility(View.VISIBLE);
                    rlRecyclerViewAllHistory.setVisibility(View.GONE);
                    tvClearHistory.setVisibility(View.GONE);
                    ((LinearLayoutManager)rlRecyclerViewHistory.getLayoutManager()).scrollToPositionWithOffset(0,0);
                    trainHistoryAdapter.notifyItemChanged(0);
                } else {
                    rlRecyclerViewAllHistory.setVisibility(View.VISIBLE);
                    rlRecyclerViewHistory.setVisibility(View.GONE);
                    tvClearHistory.setVisibility(View.VISIBLE);
                    ((LinearLayoutManager) rlRecyclerViewAllHistory.getLayoutManager()).scrollToPositionWithOffset(0, 0);
                    trainAllHistoryAdapter.notifyItemChanged(0);
                }
                if (data.size() > 5) {
                    if (!"隐藏更多".equals(tvShowOrClearHistory.getText().toString())) {
                    tvShowOrClearHistory.setText("显示更多");
                    }
                } else {
                    tvShowOrClearHistory.setText("清除历史");
                }
                Intent intent = new Intent(mActivity, TrainInfoByStationActivity.class);
                intent.putExtra("startStation",stationStart);
                intent.putExtra("endStation",stationEnd);
                startActivity(intent);

                break;
            case R.id.tv_query_train_number:
                listener.onTrainNoQueryClick();
                SPUtil.setSP("isShowMore",mActivity,isShowMore);
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
                    isShowMore = true;
                } else if ("隐藏更多".equals(showOrClear)) {
                    rlRecyclerViewHistory.setVisibility(View.VISIBLE);
                    rlRecyclerViewAllHistory.setVisibility(View.GONE);
                    tvClearHistory.setVisibility(View.GONE);
                    tvShowOrClearHistory.setText("显示更多");
                    isShowMore = false;
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
        List<TrainHistoryEntity> trainHistoryEntities = dbManager.retrieveAllHistory();
        trainHistoryAdapter.updateData(trainHistoryEntities);
        trainAllHistoryAdapter.updateData(trainHistoryEntities);
        if (trainHistoryEntities != null && trainHistoryEntities.size() > 0) {
            if (trainHistoryEntities.size() > 5) {
                tvShowOrClearHistory.setText("显示更多");
            } else {
                tvShowOrClearHistory.setText("清除历史");
            }
        }
        rlRecyclerViewAllHistory.setVisibility(View.GONE);
        isShowMore = false;
    }

}
