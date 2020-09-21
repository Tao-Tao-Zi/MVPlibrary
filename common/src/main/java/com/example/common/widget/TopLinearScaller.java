package com.example.common.widget;

import android.content.Context;
import android.support.v7.widget.LinearSmoothScroller;
/**指定recyclerviewitem时,由于recyclerview的默认机制
    是只要item显示出来就算滑动到指定item的位置
    当前类可保证指定的item滑动到顶部
 */
public class TopLinearScaller extends LinearSmoothScroller {
    public TopLinearScaller(Context context) {
        super(context);
    }

    @Override
    protected int getHorizontalSnapPreference() {
        return SNAP_TO_START;
    }

    @Override
    protected int getVerticalSnapPreference() {
        return SNAP_TO_START;
    }
}
