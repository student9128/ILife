package com.kevin.live.http;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> for Project ILife on 2017/8/25.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class VolleyUtil {
    private static RequestQueue requestQueue;
    private final static int TIME_OUT = 15000;

    public VolleyUtil() {
    }

    private static RequestQueue getInstance(Context context) {
        if (requestQueue == null) {
            synchronized (VolleyUtil.class) {
                if (requestQueue == null) {
                    requestQueue = Volley.newRequestQueue(context);
                    requestQueue.start();
                }
            }
        }
        return requestQueue;
    }

    private static <T> void addRequest(RequestQueue requestQueue,
                                       Request<T> request, Object tag) {
        if (tag != null) {
            request.setTag(tag);
        }
        request.setShouldCache(false);
        request.setRetryPolicy(new DefaultRetryPolicy(TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }

    public static void cancelAllByTag(Object tag) {
        if (null != requestQueue) {
            if (tag != null) {
                requestQueue.cancelAll(tag);
            }
        }
    }

    public static void cancelAll(Context context) {
        if (null != requestQueue) {
            requestQueue.cancelAll(context);
        }
    }

//    /**
//     *
//     * @param context
//     * @param url 接口地址
//     * @param tag
//     * @param params 参数
//     * @param clazz bean类
//     * @param listener
//     * @param <T>
//     */
//    public static <T> void stringRequestByPost(Context context,
//                                               String url, Object tag, final Map<String, String> params,
//                                               final Class<T> clazz, final HttpResponseListener<T> listener) {
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                T t = JSON.parseObject(response, clazz);
//                listener.onSuccess(t);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                if (error != null) {
//                    error.printStackTrace();
//                    listener.onFailure(error);
//                }
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                return params == null ? super.getParams() : params;
//            }
//        };
//        addRequest(getInstance(context), stringRequest, tag);
//    }

    public static <T> void stringRequestByPost(Context context,
                                               String url, Object tag, final Map<String, String> params,
                                               final HttpResponseListener<T> listener) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error != null) {
                    error.printStackTrace();
                    listener.onFailure(error);
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params == null ? super.getParams() : params;
            }
        };
        addRequest(getInstance(context), stringRequest, tag);
    }

    public static <T> void stringRequestByGet(Context context, String url, Object tag, final Map<String, String> params,
                                              final HttpResponseListener<T> listener) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onSuccess(response);
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error != null) {
                    error.printStackTrace();
                    listener.onFailure(error);
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params == null ? super.getParams() : params;
            }
        };
        addRequest(getInstance(context), stringRequest, tag);
    }

    public static <T> void jsonRequest(Context context, int method,
                                       String url, String jsonStr, Object tag,
                                       HttpResponseListener<T> listener) {

    }

    public static <T> void jsonObjectRequest(Context context, int method,
                                             String url, JSONObject requestBody, Object tag, final Map<String, String> params,
                                             final HttpResponseListener<T> listener) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(method, url, requestBody, new Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String string = response.toString();
                listener.onSuccess(string);
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error != null) {
                    error.printStackTrace();
                    listener.onFailure(error);
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params == null ? super.getParams() : params;
            }
        };
        addRequest(getInstance(context), jsonObjectRequest, tag);

    }

    public static <T> void jsonArrayRequest(Context context, int method,
                                            String url, JSONArray requestBody, Object tag, final Map<String, String> params,
                                            final HttpResponseListener<T> listener) {
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(method, url, requestBody, new Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String string = response.toString();
                listener.onSuccess(string);
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error != null) {
                    error.printStackTrace();
                    listener.onFailure(error);
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params == null ? super.getParams() : params;
            }
        };
        addRequest(getInstance(context), jsonObjectRequest, tag);

    }



}
