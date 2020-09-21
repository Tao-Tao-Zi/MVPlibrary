package com.example.common.mvp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import androidx.lifecycle.LifecycleOwner;

public interface IPresenter extends LifecycleObserver {

    //与activity生命周期里的oncreate绑定执行该方法
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void init();

    //当activity执行到destory时调用该方法
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void destory();
}
