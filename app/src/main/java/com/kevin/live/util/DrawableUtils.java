package com.kevin.live.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import java.util.Random;

/**
 * Created by Kevin on 2018/8/14<br/>
 * Blog:http://student9128.top/<br/>
 * Describe:<br/>
 */
public class DrawableUtils {
    public static GradientDrawable backgroundShape(Context context) {
        Random random = new Random();
        int r=random.nextInt(220)+50;
        int g=random.nextInt(220)+50;
        int b = random.nextInt(220)+50;
//		ShapeDrawable
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(DisplayUtils.dip2px(context, 2));//   直接写是px
        gradientDrawable.setColor(0xffffffff);
        gradientDrawable.setStroke(DisplayUtils.dip2px(context, 1), Color.rgb(r,g,b));
        return gradientDrawable;
    }
}
