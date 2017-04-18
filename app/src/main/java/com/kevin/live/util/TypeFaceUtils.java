package com.kevin.live.util;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by <b><a style="color:#8BC34A"href="http://blog.csdn.net/student9128">Kevin</a></b> on 2017/4/18.
 * <br/><b>Blog:</b>
 * <div style="color:#8BC34A"><i>http://blog.csdn.net/student9128.</i></div>
 * <p style="color:#4CAF50"><b>Description:</b>
 * <br/>
 * </p >
 */


public class TypeFaceUtils {
    public static void setTypeface(Context context, TextView textView) {
        Typeface type = Typeface.createFromAsset(context.getAssets(), "VastShadow-Regular.ttf");
        textView.setTypeface(type);
    }
}
