package com.kevin.live.util;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.kevin.live.R;


/**
 * 工具类
 */
public class UIUtils {

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 动态创建TextView
     * @param text      内容
     * @param tvWidth   宽度
     * @param mContext  上下文
     * @return
     */
    public static TextView newTextViewAndSetText(String text, int tvWidth, Context mContext) {
        TextView tv = new TextView(mContext);
        LinearLayout.LayoutParams lp= new LinearLayout.LayoutParams(tvWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        tv.setLayoutParams(lp);
        tv.setGravity(Gravity.CENTER);
        tv.setTextAppearance(mContext, R.style.TextViewStyle);
        tv.setBackgroundResource(R.drawable.shape_textview_bg_blue);
        tv.setText(text);
        return tv;
    }

    public static TextView newTextViewAndSetText(String text, int tvWidth,boolean isTwoLevel, Context mContext) {
        TextView tv = new TextView(mContext);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(tvWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        if (isTwoLevel) {//二级或三级
            lp = new LinearLayout.LayoutParams(tvWidth,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
        } else {//一级
            lp = new LinearLayout.LayoutParams(tvWidth,
                    LinearLayout.LayoutParams.MATCH_PARENT);
        }
        tv.setLayoutParams(lp);
        tv.setGravity(Gravity.CENTER);
        tv.setTextAppearance(mContext, R.style.TextViewStyle);
        tv.setBackgroundResource(R.drawable.shape_textview_bg_blue);
        tv.setText(text);
        return tv;
    }

    /**
     * 动态计算listview的宽度
     * @param listView
     */
    public static void setListViewBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        int maxWidth = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
            int width = listItem.getMeasuredWidth();
            if(width>maxWidth)maxWidth = width;
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        params.width = maxWidth;
        listView.setLayoutParams(params);
    }

    /**
     * 动态计算listview的高度，结果scrollview 嵌套 listview 显示的问题
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        /**
         * getAdapter这个方法主要是为了获取到ListView的数据条数，所以设置之前必须设置Adapter
         */
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {

            View listItem = listAdapter.getView(i, null, listView);
            //计算每一项的高度
            listItem.measure(0, 0);
            //总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        //真正的高度需要加上分割线的高度
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

}
