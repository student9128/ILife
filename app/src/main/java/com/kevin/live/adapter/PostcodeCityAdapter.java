package com.kevin.live.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kevin.live.R;
import com.kevin.live.bean.PostcodeCityBean;
import com.kevin.live.util.FontManager;
import com.kevin.live.util.LogK;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kevin on 2018/8/28<br/>
 * Blog:http://student9128.top/<br/>
 * Describe:<br/>
 */
public class PostcodeCityAdapter extends RecyclerView.Adapter<PostcodeCityAdapter.ViewHolder> {

    private Context context;
    private List<PostcodeCityBean.ResultBean.CityBean> data;
    private int index = -1;

    public PostcodeCityAdapter(Context context, List<PostcodeCityBean.ResultBean.CityBean> data) {
        this.context = context;
        this.data = data;
    }
    public void resetIndex() {
        index = -1;
        notifyDataSetChanged();
    }
    public void updateProvince(List<PostcodeCityBean.ResultBean.CityBean> d) {
        data.clear();
        data.addAll(d);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_postcode_province, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvProvince.setText(data.get(position).getCity());
        holder.llContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = position;
                if (listener != null) {
                    listener.onCityItemClickListener(position);
                }
                notifyDataSetChanged();
            }
        });
        if (index == position) {
            holder.tvProvince.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
        } else {
            holder.tvProvince.setTextColor(ContextCompat.getColor(context, R.color.black));
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvProvince;
        @BindView(R.id.tv_indicator)
        TextView tvIndicator;
        @BindView(R.id.ll_container)
        RelativeLayout llContainer;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            FontManager.fontIcon(llContainer, context);
        }
    }

    public interface OnCityClickListener {
        void onCityItemClickListener(int position);
    }

    private OnCityClickListener listener;

    public void setOnCityClickListener(OnCityClickListener l) {
        listener = l;
    }
}
