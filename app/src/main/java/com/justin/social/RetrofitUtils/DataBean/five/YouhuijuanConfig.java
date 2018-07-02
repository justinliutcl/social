package com.justin.social.RetrofitUtils.DataBean.five;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;

import java.util.List;

/**
 * Created by ASUS on 2018/4/10.
 */

public class YouhuijuanConfig extends BaseConfig{
    private List<YouhuijuanConfig> data;
    private String couponId;
    private String userId;
    private String couponValue;
    private String couponGetTime;
    private String couponContinuedTime;

    public List<YouhuijuanConfig> getData() {
        return data;
    }

    public void setData(List<YouhuijuanConfig> data) {
        this.data = data;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(String couponValue) {
        this.couponValue = couponValue;
    }

    public String getCouponGetTime() {
        return couponGetTime;
    }

    public void setCouponGetTime(String couponGetTime) {
        this.couponGetTime = couponGetTime;
    }

    public String getCouponContinuedTime() {
        return couponContinuedTime;
    }

    public void setCouponContinuedTime(String couponContinuedTime) {
        this.couponContinuedTime = couponContinuedTime;
    }
}
