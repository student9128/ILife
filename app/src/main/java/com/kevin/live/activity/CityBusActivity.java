package com.kevin.live.activity;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kevin.live.R;
import com.kevin.live.base.BaseActivity;
import com.kevin.live.bean.MobileNumberLookUpBean;
import com.kevin.live.http.Urls;
import com.kevin.live.util.LogK;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by <b><a style="color:#8BC34A"href="http://blog.csdn.net/student9128">Kevin</a></b> on 2017/5/8.
 * <br/><b>Blog:</b>
 * <div style="color:#8BC34A"><i>http://blog.csdn.net/student9128.</i></div>
 * <p style="color:#4CAF50"><b>Description:</b>
 * <p>
 * </p>
 */


public class CityBusActivity extends BaseActivity {

    private RequestQueue mQueue;

    @Override
    public int setLayoutResId() {
        return R.layout.activity_city_bus;
    }

    @Override
    public void initView() {
        mQueue = Volley.newRequestQueue(this);
    }

    @Override
    public void initData() {
        doPostQryCityBus();
    }

    @Override
    public void initListener() {

    }

    private void doPostQryCityBus() {
        String url = "https://interface.meiriyiwen.com/article/today?dev=1";
        StringRequest sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LogK.i("TAG", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                LogK.i("TAG", error.getMessage());
            }
        });
        mQueue.add(sr);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Urls.CITY_BUS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LogK.i("TAG", response);
//                Gson gson = new Gson();
//                MobileNumberLookUpBean mobileNumberLookUpBean = gson.fromJson(response, MobileNumberLookUpBean.class);
                MobileNumberLookUpBean mobileNumberLookUpBean = JSON.parseObject(response, MobileNumberLookUpBean.class);
//                int errorCode = mobileNumberLookUpBean.getError_code();
//                String reason = mobileNumberLookUpBean.getReason();
//                if (0 == errorCode) {
//                    MobileNumberLookUpBean.ResultBean result = mobileNumberLookUpBean.getResult();
//                    String mobilearea = result.getMobilearea();
//                    String mobiletype = result.getMobiletype();
//                    String areacode = result.getAreacode();
//                    String postcode = result.getPostcode();
//                } else {
//                    showToast("您的手机号不正确，请重新输入");
//                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showToast("请求失败");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("key", "8cfd01b904cf426ab940bdb65f1dccee");
                map.put("city", "北京");
                map.put("station", "五道口");
                map.put("dtype", "JSON");
                return map;
            }
        };

        mQueue.add(stringRequest);
    }

}
