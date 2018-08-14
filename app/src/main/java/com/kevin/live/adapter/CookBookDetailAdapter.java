package com.kevin.live.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kevin.live.R;
import com.kevin.live.bean.CookBookDetailBean;
import com.kevin.live.listener.OnRecyclerItemClickListener;
import com.kevin.live.util.DisplayUtils;
import com.kevin.live.util.DrawableUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kevin on 2018/8/14<br/>
 * Blog:http://student9128.top/<br/>
 * Describe:<br/>
 */
public class CookBookDetailAdapter extends RecyclerView.Adapter<CookBookDetailAdapter.ViewHolder> {

    private Context context;
    private List<CookBookDetailBean.ResultBean.ListBean> data;

    public CookBookDetailAdapter(Context context, List<CookBookDetailBean.ResultBean.ListBean> data) {
        this.context = context;
        this.data = data;
    }

    public void updateData(List<CookBookDetailBean.ResultBean.ListBean> d) {
        data.clear();
        data.addAll(d);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_item_cook_book_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        CookBookDetailBean.ResultBean.ListBean listBean = data.get(position);
        String ctgTitles = listBean.getCtgTitles();
        String[] split = ctgTitles.split(",");
        holder.tvCategory.removeAllViews();
        for (int i = 0; i < split.length; i++) {
            if (i < 5) {
                TextView textView = new TextView(context);
                textView.setText(split[i]);
                textView.setTextColor(0xff000000);
                textView.setTextSize(12);
                textView.setGravity(Gravity.CENTER);
                textView.setMinWidth(DisplayUtils.dip2px(context, 20));
                textView.setPadding(DisplayUtils.dip2px(context, 2), DisplayUtils.dip2px(context, 2), DisplayUtils.dip2px(context, 2), DisplayUtils.dip2px(context, 2));
                textView.setBackground(DrawableUtils.backgroundShape(context));
                View view = new View(context);
                view.setLayoutParams(new LinearLayout.LayoutParams(DisplayUtils.dip2px(context, 5), ViewGroup.LayoutParams.WRAP_CONTENT));
                holder.tvCategory.addView(view);
                holder.tvCategory.addView(textView);
            }
        }
        holder.tvTitle.setText(listBean.getName());
        String thumbnail = listBean.getThumbnail();
        Glide.with(context)
                .load(thumbnail)
                .centerCrop()
                .placeholder(R.drawable.ic_life_place_holder)
                .error(R.drawable.ic_image_failed_place_holder)
                .into(holder.ivThumbnail);
        holder.rlContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onRecyclerItemClickListener(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_thumbnail)
        ImageView ivThumbnail;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_category)
        LinearLayout tvCategory;
        @BindView(R.id.ll_container)
        LinearLayout llContainer;
        @BindView(R.id.rl_container)
        RelativeLayout rlContainer;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private OnRecyclerItemClickListener listener;
    public void setOnCookItemClickListener(OnRecyclerItemClickListener l) {
        listener = l;
    }
}
