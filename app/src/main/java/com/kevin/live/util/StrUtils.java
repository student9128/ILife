package com.kevin.live.util;

/**
 * Created by <b><a style="color:#8BC34A"href="http://blog.csdn.net/student9128">Kevin</a></b> on 2017/3/28.
 * <br/><b>Blog:</b>
 * <div style="color:#8BC34A"><i>http://blog.csdn.net/student9128.</i></div>
 * <p style="color:#4CAF50"><b>Description:</b>
 * <br/>
 * </p >
 */


public class StrUtils {
    public boolean isEmpty(String str) {
        if (str == null || "".equals(str)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isNotEmpty(String str) {
        if (str == null || "".equals(str)) {
            return false;
        } else {
            return true;
        }
    }
}
