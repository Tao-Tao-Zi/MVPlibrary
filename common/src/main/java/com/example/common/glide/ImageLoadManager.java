package com.example.common.glide;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.common.R;

public class ImageLoadManager {

    public static void loadImg(String url, ImageView imageView){
        LogUtils.e("ImageLoadManager->loadImg"+Constant.BASE_IMAGE+url);
        Glide.with(imageView.getContext()).load(Constant.BASE_IMAGE+url).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher).into(imageView);
    }
    public static void loadCommunityImg(String url, ImageView img){
        Glide.with(img.getContext()).load(Constant.BASE_IMAGE+url+".jpg").centerCrop().
                into(img);
    }
    //针对瀑布流延时动态布局的方法
    public static void loadGallImg(String url,ImageView imageView){
        Glide.with(imageView.getContext()).load(Constant.BASE_IMAGE+url)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        ViewGroup.LayoutParams lp = imageView.getLayoutParams();
                            int width = imageView.getMeasuredWidth();
                            float scall = (float)width/(float)resource.getIntrinsicWidth();
                            float height = resource.getIntrinsicHeight()*scall;
                            int imagHeight = Math.round(height);
                            lp.height = imagHeight;
                            imageView.setLayoutParams(lp);
                        return false;
                    }
                }).into(imageView);
    }
}
