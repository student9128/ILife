package com.kevin.live.http;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> for Project ILife on 2017/8/25.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public interface HttpResponseListener<T> {
//    void onSuccess(T response);

    void onSuccess(String response);

    void onFailure(Exception e);
}
