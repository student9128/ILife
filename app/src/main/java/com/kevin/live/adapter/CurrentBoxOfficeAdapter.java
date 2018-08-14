package com.kevin.live.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kevin.live.R;
import com.kevin.live.bean.CurrentBoxOfficeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kevin on 2018/8/13<br/>
 * Blog:http://student9128.top/<br/>
 * Describe:<br/>
 * 实时票房adapter
 */
public class CurrentBoxOfficeAdapter extends RecyclerView.Adapter<CurrentBoxOfficeAdapter.ViewHolder> {

    private List<CurrentBoxOfficeBean.ResultBean> data;

    public CurrentBoxOfficeAdapter(List<CurrentBoxOfficeBean.ResultBean> data) {
        this.data = data;
    }

    public void updateData(List<CurrentBoxOfficeBean.ResultBean> d) {
        data.clear();
        data.addAll(d);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_current_box_office, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CurrentBoxOfficeBean.ResultBean resultBean = data.get(position);
        holder.tvName.setText(resultBean.getName());
        holder.tvDays.setText(String.valueOf(resultBean.getDays()));
        holder.tvCur.setText(String.valueOf(resultBean.getCur()));
        holder.tvSum.setText(String.valueOf(resultBean.getSum()));

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_cur)
        TextView tvCur;
        @BindView(R.id.tv_days)
        TextView tvDays;
        @BindView(R.id.tv_sum)
        TextView tvSum;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
