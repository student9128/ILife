package com.kevin.live.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kevin.live.R;
import com.kevin.live.bean.PostcodeCityBean;
import com.kevin.live.listener.OnRecyclerItemClickListener;
import com.kevin.live.util.FontManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kevin on 2018/8/28<br/>
 * Blog:http://student9128.top/<br/>
 * Describe:<br/>
 */
public class PostcodeProvinceAdapter extends RecyclerView.Adapter<PostcodeProvinceAdapter.ViewHolder> {

    private Context context;
    private List<PostcodeCityBean.ResultBean> data;
    private int index = -1;

    public PostcodeProvinceAdapter(Context context, List<PostcodeCityBean.ResultBean> data) {
        this.context = context;
        this.data = data;
    }

    public void updateProvince(List<PostcodeCityBean.ResultBean> d) {
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
        holder.tvProvince.setText(data.get(position).getProvince());
        holder.llContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = position;
                if (listener != null) {
                    listener.onProvinceItemClickListener(position);
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
            FontManager.fontIcon(tvIndicator, context);
        }
    }

    public interface OnProvinceClickListener {
        void onProvinceItemClickListener(int position);
    }

    private OnProvinceClickListener listener;

    public void setOnProvinceClickListener(OnProvinceClickListener l) {
        listener = l;
    }
}
