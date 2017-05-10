package com.kevin.live.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kevin.live.R;
import com.kevin.live.bean.JokeByTimeBean;

import java.util.List;

/**
 * Created by <b><a style="color:#8BC34A"href="http://blog.csdn.net/student9128">Kevin</a></b> on 2017/5/10.
 * <br/><b>Blog:</b>
 * <div style="color:#8BC34A"><i>http://blog.csdn.net/student9128.</i></div>
 * <p style="color:#4CAF50"><b>Description:</b>
 * <br/>
 * </p >
 */


public class JokeByTimeAdapter extends RecyclerView.Adapter<JokeByTimeAdapter.MyViewHolder> {
    private Context context;
    private List<JokeByTimeBean.ResultBean> data;

    public JokeByTimeAdapter(Context context, List<JokeByTimeBean.ResultBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_item_joke, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        JokeByTimeBean.ResultBean resultBean = data.get(position);
        holder.tvContent.setText(resultBean.getContent());
        holder.tvUpdateTime.setText(resultBean.getUpdatetime());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvContent;
        private TextView tvUpdateTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.tv_joke_content);
            tvUpdateTime = (TextView) itemView.findViewById(R.id.tv_joke_update_time);

        }
    }
}
