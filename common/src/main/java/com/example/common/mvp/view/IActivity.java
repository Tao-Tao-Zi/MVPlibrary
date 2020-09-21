package com.example.common.mvp.view;

public interface IActivity {
    //初始化
    void initView();
    void initData();
    //xml
    int bindLayout();
    //初始化dagger2
    void initInject();
}
