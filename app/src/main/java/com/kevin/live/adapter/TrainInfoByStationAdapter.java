package com.kevin.live.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kevin.live.R;
import com.kevin.live.bean.TrainInfoByStationBean;
import com.kevin.live.util.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kevin on 2018/8/31<br/>
 * Blog:http://student9128.top/<br/>
 * Describe:<br/>
 */
public class TrainInfoByStationAdapter extends RecyclerView.Adapter {


    public enum ItemType {
        PU_KAI, TE_KAI, GAO_TIE, DO_CHE
    }

    private Context context;
    private List<TrainInfoByStationBean.ResultBean> data;

    public TrainInfoByStationAdapter(Context context, List<TrainInfoByStationBean.ResultBean> data) {
        this.context = context;
        this.data = data;
    }

    public void updateData(List<TrainInfoByStationBean.ResultBean> d) {
        data.clear();
        data.addAll(d);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == ItemType.PU_KAI.ordinal()) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_item_train_info_station_pu_kuai, parent, false);
            return new PViewHolder(view);
        } else if (viewType == ItemType.TE_KAI.ordinal()) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_item_train_info_station_te_kuai, parent, false);
            return new TViewHolder(view);
        } else if (viewType == ItemType.GAO_TIE.ordinal()) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_item_train_info_station_gao_tie, parent, false);
            return new GViewHolder(view);
        } else if (viewType == ItemType.DO_CHE.ordinal()) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_item_train_info_station_dong_che, parent, false);
            return new DViewHolder(view);
        } else {
            ToastUtils.showToast(context, "请检查返回数据");
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TrainInfoByStationBean.ResultBean resultBean = data.get(position);
        String lishi = resultBean.getLishi();
        String[] split = lishi.split(":");
        if (holder instanceof PViewHolder) {
            ((PViewHolder) holder).tvRwP.setText(resultBean.getPricerw());
            ((PViewHolder) holder).tvWzP.setText(resultBean.getPricewz());
            ((PViewHolder) holder).tvYwP.setText(resultBean.getPriceyw());
            ((PViewHolder) holder).tvYzP.setText(resultBean.getPriceyz());
            ((PViewHolder) holder).tvTrainNumber.setText(resultBean.getStationTrainCode());
            ((PViewHolder) holder).tvStartStation.setText(resultBean.getStartStationName());
            ((PViewHolder) holder).tvEndStation.setText(resultBean.getEndStationName());
            ((PViewHolder) holder).tvStartTime.setText(resultBean.getStartTime());
            ((PViewHolder) holder).tvArriveTime.setText(resultBean.getArriveTime());
            if (split[0] == "00") {
                ((PViewHolder) holder).tvTakeTime.setText(split[1] + "分钟");
            } else {
                ((PViewHolder) holder).tvTakeTime.setText(split[0] + "小时" + split[1] + "分钟");
            }
        } else if (holder instanceof TViewHolder) {
            ((TViewHolder) holder).tvGr.setText(resultBean.getPricegrw());
            ((TViewHolder) holder).tvRw.setText(resultBean.getPricerw());
            ((TViewHolder) holder).tvWz.setText(resultBean.getPricewz());
            ((TViewHolder) holder).tvYw.setText(resultBean.getPriceyw());
            ((TViewHolder) holder).tvYz.setText(resultBean.getPriceyz());
            ((TViewHolder) holder).tvTrainNumber.setText(resultBean.getStationTrainCode());
            ((TViewHolder) holder).tvStartStation.setText(resultBean.getStartStationName());
            ((TViewHolder) holder).tvEndStation.setText(resultBean.getEndStationName());
            ((TViewHolder) holder).tvStartTime.setText(resultBean.getStartTime());
            ((TViewHolder) holder).tvArriveTime.setText(resultBean.getArriveTime());
            if (split[0] == "00") {
                ((TViewHolder) holder).tvTakeTime.setText(split[1] + "分钟");
            } else {
                ((TViewHolder) holder).tvTakeTime.setText(split[0] + "小时" + split[1] + "分钟");
            }
        } else if (holder instanceof GViewHolder) {
            ((GViewHolder) holder).tvEd.setText(resultBean.getPriceed());
            ((GViewHolder) holder).tvSw.setText(resultBean.getPricesw());
            ((GViewHolder) holder).tvYd.setText(resultBean.getPriceyd());
            ((GViewHolder) holder).tvTrainNumber.setText(resultBean.getStationTrainCode());
            ((GViewHolder) holder).tvStartStation.setText(resultBean.getStartStationName());
            ((GViewHolder) holder).tvEndStation.setText(resultBean.getEndStationName());
            ((GViewHolder) holder).tvStartTime.setText(resultBean.getStartTime());
            ((GViewHolder) holder).tvArriveTime.setText(resultBean.getArriveTime());
            if (split[0] == "00") {
                ((GViewHolder) holder).tvTakeTime.setText(split[1] + "分钟");
            } else {
                ((GViewHolder) holder).tvTakeTime.setText(split[0] + "小时" + split[1] + "分钟");
            }
        } else if (holder instanceof DViewHolder) {
            ((DViewHolder) holder).tvRw.setText(resultBean.getPricerw());
            ((DViewHolder) holder).tvWz.setText(resultBean.getPricewz());
            ((DViewHolder) holder).tvYw.setText(resultBean.getPriceyw());
            ((DViewHolder) holder).tvYz.setText(resultBean.getPriceyz());
            ((DViewHolder) holder).tvTrainNumber.setText(resultBean.getStationTrainCode());
            ((DViewHolder) holder).tvStartStation.setText(resultBean.getStartStationName());
            ((DViewHolder) holder).tvEndStation.setText(resultBean.getEndStationName());
            ((DViewHolder) holder).tvStartTime.setText(resultBean.getStartTime());
            ((DViewHolder) holder).tvArriveTime.setText(resultBean.getArriveTime());
            if (split[0] == "00") {
                ((DViewHolder) holder).tvTakeTime.setText(split[1] + "分钟");
            } else {
                ((DViewHolder) holder).tvTakeTime.setText(split[0] + "小时" + split[1] + "分钟");
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        TrainInfoByStationBean.ResultBean resultBean = data.get(position);
        String trainClassName = resultBean.getTrainClassName();
        if ("普快".equals(trainClassName)) {
            return ItemType.PU_KAI.ordinal();
        } else if ("特快".equals(trainClassName)) {
            return ItemType.TE_KAI.ordinal();
        } else if ("高速".equals(trainClassName)) {
            return ItemType.GAO_TIE.ordinal();
        } else if ("动车".equals(trainClassName)) {
            return ItemType.DO_CHE.ordinal();
        } else {
            return ItemType.PU_KAI.ordinal();
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public static class PViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_train_number)
        TextView tvTrainNumber;
        @BindView(R.id.tv_start_station)
        TextView tvStartStation;
        @BindView(R.id.tv_start_time)
        TextView tvStartTime;
        @BindView(R.id.tv_take_time)
        TextView tvTakeTime;
        @BindView(R.id.tv_end_station)
        TextView tvEndStation;
        @BindView(R.id.tv_arrive_time)
        TextView tvArriveTime;
        @BindView(R.id.tv_rw_p)
        TextView tvRwP;
        @BindView(R.id.tv_yw_p)
        TextView tvYwP;
        @BindView(R.id.tv_yz_p)
        TextView tvYzP;
        @BindView(R.id.tv_wz_p)
        TextView tvWzP;

        public PViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class TViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_train_number)
        TextView tvTrainNumber;
        @BindView(R.id.tv_start_station)
        TextView tvStartStation;
        @BindView(R.id.tv_start_time)
        TextView tvStartTime;
        @BindView(R.id.tv_take_time)
        TextView tvTakeTime;
        @BindView(R.id.tv_end_station)
        TextView tvEndStation;
        @BindView(R.id.tv_arrive_time)
        TextView tvArriveTime;
        @BindView(R.id.tv_gr)
        TextView tvGr;
        @BindView(R.id.tv_rw)
        TextView tvRw;
        @BindView(R.id.tv_yw)
        TextView tvYw;
        @BindView(R.id.tv_yz)
        TextView tvYz;
        @BindView(R.id.tv_wz)
        TextView tvWz;

        public TViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class GViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_train_number)
        TextView tvTrainNumber;
        @BindView(R.id.tv_start_station)
        TextView tvStartStation;
        @BindView(R.id.tv_start_time)
        TextView tvStartTime;
        @BindView(R.id.tv_take_time)
        TextView tvTakeTime;
        @BindView(R.id.tv_end_station)
        TextView tvEndStation;
        @BindView(R.id.tv_arrive_time)
        TextView tvArriveTime;
        @BindView(R.id.tv_sw)
        TextView tvSw;
        @BindView(R.id.tv_yd)
        TextView tvYd;
        @BindView(R.id.tv_ed)
        TextView tvEd;

        public GViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class DViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_train_number)
        TextView tvTrainNumber;
        @BindView(R.id.tv_start_station)
        TextView tvStartStation;
        @BindView(R.id.tv_start_time)
        TextView tvStartTime;
        @BindView(R.id.tv_take_time)
        TextView tvTakeTime;
        @BindView(R.id.tv_end_station)
        TextView tvEndStation;
        @BindView(R.id.tv_arrive_time)
        TextView tvArriveTime;
        @BindView(R.id.tv_rw)
        TextView tvRw;
        @BindView(R.id.tv_yw)
        TextView tvYw;
        @BindView(R.id.tv_yz)
        TextView tvYz;
        @BindView(R.id.tv_wz)
        TextView tvWz;

        public DViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
