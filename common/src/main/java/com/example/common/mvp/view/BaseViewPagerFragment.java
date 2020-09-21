package com.example.common.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.common.mvp.presenter.IPresenter;
import com.example.common.widget.LoagDialog;

import javax.inject.Inject;

public abstract class BaseViewPagerFragment<P extends IPresenter>extends Fragment implements IView,IFragment{
    private boolean firstFlog = true;
    protected int categoryid = -1;
    private boolean isVisibleToUser = false;
    @Inject
    protected P mPresenter;
    private View inLayout;
    protected LoagDialog dialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inLayout = LayoutInflater.from(getContext()).inflate(bindLayout(),container,false);
        dialog = new LoagDialog(getContext());
        initView();
        initData();
        initInject();
        getLifecycle().addObserver(mPresenter);
        if (isVisibleToUser && firstFlog){
            firstFlog = false;
            initPrelod();
        }
        return inLayout;
    }


    @Override
    public <T extends View> T findViewById(int id) {
        return inLayout.findViewById(id);
    }

    @Override
    public void onDestroy() {
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

    //懒加载
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        Log.e("tzr", "initPrelod: "+this.isVisibleToUser+""+firstFlog+""+categoryid);
        if (this.isVisibleToUser)
                    initPrelod();
    }

    public abstract void initPrelod();

    @Override
    public void onResume() {
        super.onResume();
        if (isVisibleToUser && firstFlog){
            firstFlog = false;
            initPrelod();
        }
    }
}
