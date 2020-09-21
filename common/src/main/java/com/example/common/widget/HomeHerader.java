package com.example.common.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

public class HomeHerader extends LinearLayout implements RefreshHeader {
private TextView mHeaderText;
    public HomeHerader(Context context) {
        super(context);
        setOrientation(VERTICAL);
        mHeaderText = new TextView(context);
        mHeaderText.setText("下拉刷新");
        LayoutParams lp = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ConvertUtils.dp2px(100)
        );
        mHeaderText.setLayoutParams(lp);
        mHeaderText.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM);
        mHeaderText.setPadding(0,0,0,ConvertUtils.dp2px(10));
        mHeaderText.setTextSize(20);
        mHeaderText.setTextColor(Color.parseColor("#c1c1c1"));
        addView(mHeaderText);
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }
    //判断位置
    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {
        if (listener != null){
            listener.pull(percent);
        }
    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }
    //
    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        return 500;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }
//
    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        switch (newState) {
            case None:
            case PullDownToRefresh:
                mHeaderText.setText("下拉刷新");
                break;
            case Refreshing:
                mHeaderText.setText("正在刷新");
                break;
            case ReleaseToRefresh:
                mHeaderText.setText("释放立即刷新");
                break;
        }
    }

    private HeaderPullListener listener;

    public void setListener(HeaderPullListener listener) {
        this.listener = listener;
    }

    public interface HeaderPullListener{
        void pull(float percent);
    }
}
