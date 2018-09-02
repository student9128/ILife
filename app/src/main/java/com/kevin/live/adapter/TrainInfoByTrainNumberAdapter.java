package com.kevin.live.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kevin.live.R;
import com.kevin.live.bean.TrainInfoByTrainNoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kevin on 2018/9/2<br/>
 * Blog:http://student9128.top/<br/>
 * Describe:<br/>
 */
public class TrainInfoByTrainNumberAdapter extends RecyclerView.Adapter {


    public enum ItemType {
        HEADER, CONTENT
    }

    private View headerView;

    private Context context;
    private List<TrainInfoByTrainNoBean.ResultBean> data;

    public TrainInfoByTrainNumberAdapter(Context context, List<TrainInfoByTrainNoBean.ResultBean> data) {
        this.context = context;
        this.data = data;
    }

    public void updateData(List<TrainInfoByTrainNoBean.ResultBean> d) {
        data.clear();
        data.addAll(d);
        notifyDataSetChanged();
    }

    public void addHeaderView(View view) {
        headerView = view;
        notifyItemInserted(0);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == ItemType.HEADER.ordinal()) {
            return new HViewHolder(headerView);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_item_train_info_number, parent, false);
            return new CViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == ItemType.HEADER.ordinal()) {
            return;
        }
        position = position - 1;
        TrainInfoByTrainNoBean.ResultBean resultBean = data.get(position);
        if (holder instanceof CViewHolder) {
            ((CViewHolder) holder).tvStationNumber.setText(String.valueOf(position));
            ((CViewHolder) holder).tvStationName.setText(resultBean.getStationName());
            ((CViewHolder) holder).tvStartTime.setText(resultBean.getStartTime());
            ((CViewHolder) holder).tvArriveTime.setText(resultBean.getArriveTime());
            ((CViewHolder) holder).tvRemainTime.setText(resultBean.getArriveTime());
        }
    }

    @Override
    public int getItemCount() {
        int itemCount = data.size();
        if (null != headerView) {
            itemCount++;
        }
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (null != headerView && 0 == position) {
            return ItemType.HEADER.ordinal();
        } else {
            return ItemType.CONTENT.ordinal();
        }
    }

    public class HViewHolder extends RecyclerView.ViewHolder {
        public HViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class CViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_station_number)
        TextView tvStationNumber;
        @BindView(R.id.tv_station_name)
        TextView tvStationName;
        @BindView(R.id.tv_arrive_time)
        TextView tvArriveTime;
        @BindView(R.id.tv_start_time)
        TextView tvStartTime;
        @BindView(R.id.tv_remain_time)
        TextView tvRemainTime;

        public CViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
