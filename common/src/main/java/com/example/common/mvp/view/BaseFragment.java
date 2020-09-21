package com.example.common.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.example.common.mvp.presenter.IPresenter;
import com.example.common.widget.LoagDialog;
import com.yatoooon.screenadaptation.ScreenAdapterTools;

import javax.inject.Inject;

public abstract class BaseFragment<P extends IPresenter> extends Fragment implements IView,IFragment {
    @Inject
    protected P mPresenter;
    private View inLayout;
    protected LoagDialog dialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inLayout = LayoutInflater.from(getContext()).inflate(bindLayout(),container,false);
        ScreenAdapterTools.getInstance().loadView(inLayout);
        return inLayout;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dialog = new LoagDialog(getContext());
        initView();
        initData();
        initInject();
        getLifecycle().addObserver(mPresenter);
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
}
