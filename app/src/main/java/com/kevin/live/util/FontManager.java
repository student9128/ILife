package com.kevin.live.util;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Kevin on 2018/7/9.
 * <p>
 * Blog:https://student9128.github.io/
 * CSDN:https://blog.csdn.net/student9128
 * <p/>
 */
public class FontManager {
    public static void fontIcon(View v, Context context) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fontawesome-webfont" +
                ".ttf");
        if (v instanceof ViewGroup) {
            for (int i=0;i<((ViewGroup) v).getChildCount();i++) {
                View child = ((ViewGroup) v).getChildAt(i);
                fontIcon(child,context);
            }
        } else if (v instanceof TextView) {
            ((TextView) v).setTypeface(typeface);
        } else if (v instanceof Button) {
            ((Button) v).setTypeface(typeface);
        } else if (v instanceof EditText) {
            ((EditText) v).setTypeface(typeface);
        }
    }
}
