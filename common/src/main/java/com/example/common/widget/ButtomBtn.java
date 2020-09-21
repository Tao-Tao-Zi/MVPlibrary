package com.example.common.widget;

import android.content.ContentUris;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.blankj.utilcode.util.ConvertUtils;
import com.example.common.R;

//底部的buttom
public class ButtomBtn extends RelativeLayout implements View.OnClickListener {
    //存放位置
    private LinearLayout linear;
    //红点
    private ImageView pointImg;

    private ImageView iconImg;
    private TextView nameTv;

    private int nomalColor;
    private int selectColor;

    private int nomalRes;
    private int selRes;

    private BottomSelListener bottomSelListener;

    public ButtomBtn setBottomSelListener(BottomSelListener bottomSelListener) {
        this.bottomSelListener = bottomSelListener;
        return this;
    }

    public ButtomBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ConvertUtils.dp2px(60),ConvertUtils.dp2px(60));

        //设置水平居中
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);

        linear = new LinearLayout(getContext());
        linear.setOnClickListener(this::onClick);
        linear.setOrientation(LinearLayout.VERTICAL);
        //设置linear在父容器里
        linear.setLayoutParams(lp);
        addView(linear);

        iconImg = new ImageView(getContext());
        iconImg.setLayoutParams(new LinearLayout.LayoutParams(ConvertUtils.dp2px(60),ConvertUtils.dp2px(60)));
        linear.addView(iconImg);

        nameTv = new TextView(getContext());
        nameTv.setGravity(Gravity.CENTER_HORIZONTAL);
        nameTv.setLayoutParams(new LinearLayout.LayoutParams(ConvertUtils.dp2px(15),ConvertUtils.dp2px(15)));
        linear.addView(nameTv);

        pointImg = new ImageView(getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(new LinearLayout.LayoutParams(ConvertUtils.dp2px(10), ConvertUtils.dp2px(10)));
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParams.leftMargin =ConvertUtils.dp2px(8);
        layoutParams.topMargin = ConvertUtils.dp2px(6);
        pointImg.setLayoutParams(layoutParams);
        pointImg.setImageResource(R.drawable.shape);
        addView(pointImg);
    }
    //红点显示
    public ButtomBtn showPoint(){
        pointImg.setVisibility(VISIBLE);
        return this;
    }
    public ButtomBtn hidePoint(){
        pointImg.setVisibility(GONE);
        return this;
    }
    //文字默认颜色
    @RequiresApi(api = Build.VERSION_CODES.O)
    public ButtomBtn nomalTextColor(String colorStr){
        nomalColor = Color.parseColor(colorStr);
        nameTv.setTextColor(nomalColor);
        return this;
    }
    //文字选中
    @RequiresApi(api = Build.VERSION_CODES.O)
    public ButtomBtn selTextColor(String colorStr){
        selectColor = Color.parseColor(colorStr);
        nameTv.setTextColor(selectColor);
        return this;
    }
    //设置iconImage默认图标
    public ButtomBtn nomalImg(int resId){
        nomalRes = resId;
        iconImg.setBackgroundResource(nomalRes);
        return this;
    }

    //设置iconImage选中图标
    public ButtomBtn sellImg(int resId){
        selRes = resId;
        iconImg.setBackgroundResource(selRes);
        return this;
    }

    //清除选中效果
    public ButtomBtn clearSel(){
        //text,ima默认
        iconImg.setBackgroundResource(nomalRes);
        nameTv.setTextColor(nomalColor);
        return this;
    }
    //添加选中效果
    public ButtomBtn sel(){
        iconImg.setBackgroundResource(selRes);
        nameTv.setTextColor(selectColor);
        return this;
    }
    //设置底部显示文字
    public ButtomBtn setNameValues(String str){
        nameTv.setText(str);
        return this;
    }

    @Override
    public void onClick(View v) {
        hidePoint();
        sel();

        if (bottomSelListener != null){
            bottomSelListener.SelectListener(getId());
        }
    }

    //回调接口监听器
    public interface BottomSelListener{
        void SelectListener(int id);
    }
}
