package com.example.common.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.blankj.utilcode.util.ToastUtils;
import com.example.common.mvp.presenter.IPresenter;
import com.example.common.widget.LoagDialog;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import javax.inject.Inject;

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IView,IActivity {

    @Inject
    protected P mPresenter;
    private LoagDialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());
        dialog = new LoagDialog(this);
        initView();
        initData();
        initInject();
        //关联订阅
        getLifecycle().addObserver(mPresenter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(mPresenter);
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void showDialog() {
        dialog.show();
    }

    @Override
    public void hideDialog() {
        dialog.hide();
    }
}
