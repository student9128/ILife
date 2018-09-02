package com.kevin.live.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kevin.live.R;
import com.kevin.live.database.TrainHistoryEntity;
import com.kevin.live.database.TrainNoHistoryEntity;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kevin on 2018/8/30<br/>
 * Blog:http://student9128.top/<br/>
 * Describe:<br/>
 */
public class TrainNoHistoryAllAdapter extends RecyclerView.Adapter<TrainNoHistoryAllAdapter.ViewHolder> {

    private Context context;
    private List<TrainNoHistoryEntity> data;
    private boolean showTime;

    public TrainNoHistoryAllAdapter(Context context, List<TrainNoHistoryEntity> data, boolean showTime) {
        this.context = context;
        this.data = data;
        this.showTime = showTime;
    }

    public void updateData(List<TrainNoHistoryEntity> d) {
        data.clear();
        data.addAll(d);
        Collections.reverse(data);
        notifyDataSetChanged();
    }

    public void insertHistory(TrainNoHistoryEntity d) {
        data.add(0,d);
//        Collections.reverse(data);
        notifyItemInserted(0);
    }

    public void deleteHistory(TrainNoHistoryEntity d, int position) {
        data.remove(d);
        notifyItemRemoved(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_item_train_history_all, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TrainNoHistoryEntity trainNoHistoryEntity = data.get(position);
        holder.tvStation.setText(trainNoHistoryEntity.getTrainNo());
        holder.tvTime.setText(trainNoHistoryEntity.getTime());
        if (showTime) {
            holder.tvTime.setVisibility(View.VISIBLE);
        } else {
            holder.tvTime.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : (showTime ? data.size() : (data.size() > 5 ? 5 : data.size()));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_station)
        TextView tvStation;
        @BindView(R.id.tv_time)
        TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
