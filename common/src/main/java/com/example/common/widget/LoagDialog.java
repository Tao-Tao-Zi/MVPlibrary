package com.example.common.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

public class LoagDialog extends Dialog {
    private ProgressBar bar;
    public LoagDialog(@NonNull Context context) {
        super(context);
        //屏蔽返回键
        setCancelable(false);
        getWindow().setDimAmount(0f);
        //设置背景透明
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
       setContentView(init());
    }

    private View init() {
        bar = new ProgressBar(getContext());
        return bar;
    }

}
