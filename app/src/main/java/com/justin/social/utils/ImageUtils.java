package com.justin.social.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by ASUS on 2018/4/2.
 */

public class ImageUtils {
    @BindingAdapter("uriImage")
    public static void setImage(ImageView view, String url){
        Glide.with(view.getContext().getApplicationContext()).load(url).into(view);
    }
}
