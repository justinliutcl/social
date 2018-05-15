package com.justin.social.RetrofitUtils.DataBean.one;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/4/5.
 */

public class OnlineServiceConfig extends BaseConfig implements Serializable{
    private List<OnlineServiceConfig> data;
    private String customServiceId;
    private String customServiceName;
    private String customServiceType;
    private String phone;
    private String headImg;
    private String email;
    private String qq;
    private String status;

    public List<OnlineServiceConfig> getData() {
        return data;
    }

    public void setData(List<OnlineServiceConfig> data) {
        this.data = data;
    }

    public String getCustomServiceId() {
        return customServiceId;
    }

    public void setCustomServiceId(String customServiceId) {
        this.customServiceId = customServiceId;
    }

    public String getCustomServiceName() {
        return customServiceName;
    }

    public void setCustomServiceName(String customServiceName) {
        this.customServiceName = customServiceName;
    }

    public String getCustomServiceType() {
        return customServiceType;
    }

    public void setCustomServiceType(String customServiceType) {
        this.customServiceType = customServiceType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
