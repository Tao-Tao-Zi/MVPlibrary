package com.example.common.widget;

import android.content.Context;
import android.widget.ImageView;

import com.example.common.glide.ImageLoadManager;
import com.youth.banner.loader.ImageLoader;

public class HomeBannerLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        String url = (String) path;
        ImageLoadManager.loadImg(url,imageView);
    }
}
