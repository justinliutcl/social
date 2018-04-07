package com.justin.social.RetrofitUtils.DataBean.two;

import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.utils.AppUtils;
import com.justin.social.utils.DimensUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/4/7.
 */

public class ServiceConfig extends BaseConfig implements Serializable{
    private List<ServiceConfig> data;
    private String serviceId;
    private String serviceName;
    private String serviceImg;
    private String color;
    private String[] txt;

    public List<ServiceConfig> getData() {
        return data;
    }

    public void setData(List<ServiceConfig> data) {
        this.data = data;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceImg() {
        return serviceImg;
    }

    public void setServiceImg(String serviceImg) {
        this.serviceImg = serviceImg;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String[] getTxt() {
        return txt;
    }

    public void setTxt(String[] txt) {
        this.txt = txt;
    }

    @BindingAdapter("customColor")
    public static void setCustomTextColor(TextView view, String color){
        view.setTextColor(Color.parseColor(color));
    }

    @BindingAdapter("customText")
    public static void setCustomText(TextView view, String[] arr){
        if(arr == null)
            return;
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<arr.length;i++){
            builder.append(arr[i]);
            if(i != arr.length-1) {
                builder.append("\n");
            }
        }
        view.setText(builder.toString());
    }

    @BindingAdapter("initMinWidth")
    public static void setTextMinWidth(TextView view, String serviceName){
        int []wh = DimensUtils.getScreenWidthHeight(view.getContext());
        int width = (int) (wh[0]*0.5-DimensUtils.dip2px(view.getContext(),40));
        view.setMinWidth(width);
    }
}
