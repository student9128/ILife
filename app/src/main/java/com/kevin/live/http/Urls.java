package com.kevin.live.http;

/**
 * Created by <b><a style="color:#8BC34A"href="http://blog.csdn.net/student9128">Kevin</a></b> on 2017/4/24.
 * <br/><b>Blog:</b>
 * <div style="color:#8BC34A"><i>http://blog.csdn.net/student9128.</i></div>
 * <p style="color:#4CAF50"><b>Description:</b>
 * <br/>
 * </p >
 */


public class Urls {
    /**
     * appkey:b09e6d69a9864de1bba1d7c52050d083
     */
    //http://api.avatardata.cn/XinHuaZiDian/LookUp字典接口
    //http://api.avatardata.cn/MobilePlace/LookUp
    public static final String APP_Key = "2746a9f225c9b";
    //http://api.avatardata.cn/MobilePlace/LookUp
    public static final String BASE_URL = "http://apicloud.mob.com/";
    public static final String CITY_BUS = BASE_URL + "CityBus/LookUp";
    public static final String MOBILE_PLACE = BASE_URL + "MobilePlace/LookUp";
    public static final String CHINESE_DICTIONARY = BASE_URL + "XinHuaZiDian/LookUp";
    public static final String JOKE_BY_TIME = BASE_URL + "Joke/QueryJokeByTime";
    public static final String JOKE_NEWS_JOKE = BASE_URL + "Joke/NewstJoke";
    public static final String JOKE_NEWS_IMG = BASE_URL + "Joke/NewstImg";
    /**
     * 手机号码归属地查询
     */
    public static final String MOBILE_NUMBER_LOCATION = BASE_URL + "v1/mobile/address/query";
    /**
     * 彩票列表
     */
    public static final String LOTTERY_LIST = BASE_URL + "lottery/list";
}
