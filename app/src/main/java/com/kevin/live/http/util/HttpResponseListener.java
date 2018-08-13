package com.kevin.live.http.util;


import com.android.volley.VolleyError;

public interface HttpResponseListener<T> {

  public void onSuccess(T t);
     
  public void onFail(VolleyError volleyError);
}
