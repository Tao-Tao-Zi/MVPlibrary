package com.example.common.mvp.presenter;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import com.blankj.utilcode.util.LogUtils;
import com.example.common.http.BaseEntity;
import com.example.common.mvp.model.IModel;
import com.example.common.mvp.view.IView;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<M extends IModel ,V extends IView> implements IPresenter, Observer<BaseEntity> {

    @Inject
    protected M mModel;//dagger注入对象
    protected V mView;
    //声明订阅
    protected CompositeDisposable compositeDisposable;
    public BasePresenter(M mModel, V mView) {
        this.mModel = mModel;
        this.mView = mView;
        compositeDisposable = new CompositeDisposable();
    }


    @Override
    public void destory() {
        compositeDisposable.clear();
        if (mView != null){
            mView = null;
        }
    }

    //订阅
    @Override
    public void onSubscribe(Disposable d) {
        compositeDisposable.add(d);
        mView.showDialog();
    }

    @Override
    public void onError(Throwable e) {
        LogUtils.e("BasePresenter->onError"+e.getMessage());
        mView.showToast(e.getMessage());
    }

    @Override
    public void onComplete() {
        mView.hideDialog();
    }
}
