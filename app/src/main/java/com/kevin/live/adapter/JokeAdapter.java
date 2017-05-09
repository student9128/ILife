package com.kevin.live.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kevin.live.R;
import com.kevin.live.bean.JokeByTimeBean;

import java.util.List;

/**
 * Created by <b><a style="color:#8BC34A"href="http://blog.csdn.net/student9128">Kevin</a></b> on 2017/5/9.
 * <br/><b>Blog:</b>
 * <div style="color:#8BC34A"><i>http://blog.csdn.net/student9128.</i></div>
 * <p style="color:#4CAF50"><b>Description:</b>
 * <p>
 * </p>
 */


public class JokeAdapter extends BaseAdapter {
    private Context context;
    private List<JokeByTimeBean.ResultBean> data;

    public JokeAdapter(Context context, List<JokeByTimeBean.ResultBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.adapter_item_joke, null);
            viewHolder = new ViewHolder();
            viewHolder.textContent = (TextView) convertView.findViewById(R.id.tv_joke_content);
            viewHolder.textTime = (TextView) convertView.findViewById(R.id.tv_joke_update_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        JokeByTimeBean.ResultBean resultBean = data.get(position);
        viewHolder.textContent.setText(resultBean.getContent());
        viewHolder.textTime.setText(resultBean.getUpdatetime());

        return convertView;
    }

    static class ViewHolder {
        TextView textContent;
        TextView textTime;
    }
}
