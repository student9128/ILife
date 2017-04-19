package com.kevin.live.util;

import android.util.Log;

import com.kevin.live.BuildConfig;


/**
 * <p>
 * Created by <b><a style="color:#8BC34A"href="http://blog.csdn.net/student9128">Kevin</ a></b> on 2017/2/16.
 * <br/><b>Blog:</b>
 * <div style="color:#8BC34A"><i>http://blog.csdn.net/student9128.</i></div>
 * <p style="color:#4CAF50"><b>Description:</b>
 * <br/>the utils for log.
 * </p >
 */

public class LogK {
    /**
     * set IS_DEBUG false when release to close log.
     */
//    private static boolean IS_DEBUG = true;
    private static boolean IS_DEBUG = BuildConfig.DEBUG;
//    private static boolean IS_DEBUG = false;

    public static void i(String tag, String message) {
        if (IS_DEBUG) {
            Log.i(tag + "-->:", message);
        }
    }

    public static void e(String tag, String message) {
        if (IS_DEBUG) {
            Log.e(tag + "-->", message);
        }
    }

    public static void w(String tag, String message) {
        if (IS_DEBUG) {
            Log.w(tag + "-->:", message);
        }
    }

    public static void v(String tag, String message) {
        if (IS_DEBUG) {
            Log.v(tag + "-->:", message);
        }
    }

    public static void d(String tag, String message) {
        if (IS_DEBUG) {
            Log.d(tag + "-->:", message);
        }
    }

}
