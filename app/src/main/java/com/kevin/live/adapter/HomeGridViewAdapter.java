package com.kevin.live.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevin.live.R;

/**
 * Created by <b><a style="color:#8BC34A"href="http://blog.csdn.net/student9128">Kevin</a></b> on 2017/4/25.
 * <br/><b>Blog:</b>
 * <div style="color:#8BC34A"><i>http://blog.csdn.net/student9128.</i></div>
 * <p style="color:#4CAF50"><b>Description:</b>
 * <br/>
 * </p >
 */


public class HomeGridViewAdapter extends BaseAdapter {
    private Context context;
    private int[] gridViewIcon;
    private String[] gridViewTitle;

    public HomeGridViewAdapter(Context context, int[] gridViewIcon, String[] gridViewTitle) {
        this.context = context;
        this.gridViewIcon = gridViewIcon;
        this.gridViewTitle = gridViewTitle;
    }

    @Override
    public int getCount() {
        return gridViewIcon == null ? 0 : gridViewIcon.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.adapter_item_grid_view, null);
            viewHolder = new ViewHolder();
            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.ivIcon.setImageResource(gridViewIcon[position]);
        viewHolder.tvTitle.setText(gridViewTitle[position]);

        return convertView;
    }

    static class ViewHolder {
        ImageView ivIcon;
        TextView tvTitle;
    }
}
