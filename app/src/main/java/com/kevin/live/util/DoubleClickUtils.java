package com.kevin.live.util;

/**
 * Created by Kevin on 2016/9/7.
 * <br/>
 * 判断是否快速点击，快速点击两次不让第二次不生效
 */
public class DoubleClickUtils {

    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long lastTime = time - lastClickTime;
        if (0 < lastTime && lastTime < 1500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
