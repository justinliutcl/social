package com.justin.social.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.justin.social.R;

/**
 * Created by ASUS on 2018/4/2.
 */

public class ImageUtils {
    @BindingAdapter("uriImage")
    public static void setImage(ImageView view, String url){
        Glide.with(view.getContext().getApplicationContext()).load(url)
                .error(R.drawable.icon_news_default)
                .placeholder(R.drawable.icon_news_default)
                .into(view);
    }

    @BindingAdapter("uriIcon")
    public static void setIcon(ImageView view, String url){
        Glide.with(view.getContext().getApplicationContext()).load(url)
                .error(R.drawable.icon_news_default)
                .placeholder(R.drawable.icon_news_default)
                .into(view);
    }

    @BindingAdapter("icon")
    public static void setResIcon(ImageView view, int res){
        Glide.with(view.getContext().getApplicationContext()).load(res)
                .into(view);
    }
}
