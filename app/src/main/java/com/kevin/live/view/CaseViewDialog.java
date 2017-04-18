package com.kevin.live.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.kevin.live.R;

/**
 * Created by <b><a style="color:#8BC34A"href="http://blog.csdn.net/student9128">Kevin</a></b> on 2017/4/7.
 * <br/><b>Blog:</b>
 * <div style="color:#8BC34A"><i>http://blog.csdn.net/student9128.</i></div>
 * <p style="color:#4CAF50"><b>Description:</b>
 * <p>
 * </p>
 */


public class CaseViewDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private TextView t;
    private TextView tipEn, tipCn;
    private Button btn;

    public CaseViewDialog(@NonNull Context context) {
        super(context, R.style.DialogFullscreen);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_case_view, null);
        setContentView(view);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        t = (TextView) view.findViewById(R.id.tv_tip);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "GloriaHallelujah.ttf");
        t.setTypeface(typeface);
        tipEn = (TextView) view.findViewById(R.id.tv_en_tip);
        Typeface type = Typeface.createFromAsset(context.getAssets(), "PoiretOne-Regular.ttf");
        tipEn.setTypeface(type);
        tipCn = (TextView) view.findViewById(R.id.tv_cn_tip);
        Typeface typeC = Typeface.createFromAsset(context.getAssets(), "Chinese.TTF");
        tipCn.setTypeface(typeC);
        btn = (Button) view.findViewById(R.id.btn_ok);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                listener.getCallBack();
                break;
        }
    }

    private Callback listener;

    public void setCallBack(Callback callBack) {
        listener = callBack;
    }
    public interface Callback {
        void getCallBack();
    }
}
