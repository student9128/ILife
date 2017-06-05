package com.kevin.live.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.kevin.live.R;

/**
 * Created by <b><a style="color:#8BC34A"href="http://blog.csdn.net/student9128">Kevin</a></b> on 2017/4/7.
 * <br/><b>Blog:</b>
 * <div style="color:#8BC34A"><i>http://blog.csdn.net/student9128.</i></div>
 * <p style="color:#4CAF50"><b>Description:</b>
 * <br/>
 * </p >
 */


public class CheckDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private View view;
    private String mTitle;
    //    private ListView mListView;
//    private List<ReportListWithDateBean.RSPBODYBean.DateArrBean> mData = new ArrayList<>();
//    private DateDialogAdapter mAdapter;
    private TextView mTvTitle, mTvCancel;
    private TextView mTvMethodOne, mTvMethodTwo;

    public CheckDialog(Context context) {
        super(context);
        this.context = context;
    }

    public CheckDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }


    //    public CheckDialog(Context context, List<ReportListWithDateBean.RSPBODYBean.DateArrBean> data) {
//        super(context);
//        this.context = context;
//        this.mData = data;
//    }
    public void setTitleText(String text) {
        this.mTitle = text;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉系统的黑色矩形边框
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initView();
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.dialog_check, null);
        setContentView(view);
        mTvTitle = (TextView) findViewById(R.id.tv_dialog_title);
        mTvCancel = (TextView) findViewById(R.id.tv_dialog_cancel);
        mTvTitle.setText(mTitle);
//        mListView = (ListView) view.findViewById(R.id.lv_date);
//        mAdapter = new DateDialogAdapter(context, mData);
//        mAdapter.setDateItemClickListener(this);
//        mListView.setAdapter(mAdapter);
        mTvMethodOne = (TextView) findViewById(R.id.tv_method_one);
        mTvMethodTwo = (TextView) findViewById(R.id.tv_method_two);

        mTvMethodOne.setOnClickListener(this);
        mTvMethodTwo.setOnClickListener(this);
        mTvCancel.setOnClickListener(this);

        Window dialogWindow = getWindow();
        dialogWindow.setWindowAnimations(R.style.CheckDialogAnimation);
        dialogWindow.setGravity(Gravity.CENTER);
//        dialogWindow.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.75); // 高度设置为屏幕的0.8
//        lp.width = d.widthPixels;
//        Display di = dialogWindow.getWindowManager().getDefaultDisplay();
//        lp.width = di.getWidth();
//        lp.height = (int) (d.widthPixels * 0.8);
        dialogWindow.setAttributes(lp);
        setCanceledOnTouchOutside(false);
    }


//    @Override
//    public void show() {
//        super.show();
//        /**
//         * 设置宽度全屏，要设置在show的后面
//         */
//        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
//        layoutParams.gravity=Gravity.BOTTOM;
//        layoutParams.width= WindowManager.LayoutParams.MATCH_PARENT;
//        layoutParams.height= WindowManager.LayoutParams.WRAP_CONTENT;
//
//        getWindow().getDecorView().setPadding(0, 0, 0, 0);
//
//        getWindow().setAttributes(layoutParams);
//    }

//    @Override
//    public void getItem(int position) {
//        mListener.itemClick(position);
////        mTextDate.setText(mData.get(position).getDATA_DATE());
//    }

    private DialogItemOnClickListener mListener;

    public void setDialogItemOnClickListener(DialogItemOnClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_dialog_cancel:
                mListener.doCancel();
                break;
            case R.id.tv_method_one:
                mListener.doUseMethodOne();
                break;
            case R.id.tv_method_two:
                mListener.doUseMethodTwo();
                break;
        }
    }

    public interface DialogItemOnClickListener {
//        void itemClick(int position);

        void doCancel();

        void doUseMethodOne();

        void doUseMethodTwo();
    }
}
