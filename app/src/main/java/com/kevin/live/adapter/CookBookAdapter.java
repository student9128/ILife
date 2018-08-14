package com.kevin.live.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.kevin.live.R;
import com.kevin.live.bean.CookBookBean;
import com.kevin.live.util.FontManager;

import java.util.List;

/**
 * Created by Kevin on 2018/8/14<br/>
 * Blog:http://student9128.top/<br/>
 * Describe:<br/>菜谱分类列表 for {@link com.kevin.live.activity.CookBookActivity}
 */
public class CookBookAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<CookBookBean.ResultBean.ChildsBeanX> data;

    public CookBookAdapter(Context context, List<CookBookBean.ResultBean.ChildsBeanX> data) {
        this.context = context;
        this.data = data;
    }

    public void updateData(List<CookBookBean.ResultBean.ChildsBeanX> d) {
        data.clear();
        data.addAll(d);
        notifyDataSetChanged();

    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(groupPosition).getChilds().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getChilds().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_item_cook_book_group, null);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.mGroupText = convertView.findViewById(R.id.tv_group);
            groupViewHolder.mGroupIndicator = convertView.findViewById(R.id.tv_indicator);
            FontManager.fontIcon(groupViewHolder.mGroupIndicator,context);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.mGroupText.setText(data.get(groupPosition).getCategoryInfo().getName());
        if (isExpanded) {
            groupViewHolder.mGroupIndicator.setText(R.string.angle_down);
        } else {
            groupViewHolder.mGroupIndicator.setText(R.string.angle_right);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_item_cook_book_child, null);
            childViewHolder = new ChildViewHolder();
            childViewHolder.mChildText = (TextView) convertView.findViewById(R.id.tv_child);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.mChildText.setText(data.get(groupPosition).getChilds().get(childPosition).getCategoryInfo().getName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class GroupViewHolder {
        TextView mGroupText;
        TextView mGroupIndicator;
    }

    static class ChildViewHolder {
        TextView mChildText;
    }
}
