package com.kevin.live.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kevin.live.R;
import com.kevin.live.bean.CookBookMethodBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kevin on 2018/8/14<br/>
 * Blog:http://student9128.top/<br/>
 * Describe:<br/>
 */
public class CookBookMethodAdapter extends RecyclerView.Adapter<CookBookMethodAdapter.ViewHolder> {

    private Context context;
    List<CookBookMethodBean.MethodBean> data;

    public CookBookMethodAdapter(Context context, List<CookBookMethodBean.MethodBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_item_cook_book_method, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CookBookMethodBean.MethodBean methodBean = data.get(position);
        holder.tvMethod.setText(methodBean.getStep());
        Glide.with(context).load(methodBean.getImg())
                .centerCrop()
                .placeholder(R.drawable.ic_life_place_holder)
                .error(R.drawable.ic_image_failed_place_holder)
                .into(holder.ivMethod);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_method)
        TextView tvMethod;
        @BindView(R.id.iv_method)
        ImageView ivMethod;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
