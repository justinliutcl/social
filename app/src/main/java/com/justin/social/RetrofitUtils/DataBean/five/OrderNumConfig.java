package com.justin.social.RetrofitUtils.DataBean.five;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;

import java.util.List;

/**
 * Created by ASUS on 2018/5/19.
 */

public class OrderNumConfig extends BaseConfig {
    public OrderNumConfig data;
    public String userId;
    public String orderNoapply;
    public String orderApply;
    public String orderAll;

    public OrderNumConfig getData() {
        return data;
    }

    public void setData(OrderNumConfig data) {
        this.data = data;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderNoapply() {
        return orderNoapply;
    }

    public void setOrderNoapply(String orderNoapply) {
        this.orderNoapply = orderNoapply;
    }

    public String getOrderApply() {
        return orderApply;
    }

    public void setOrderApply(String orderApply) {
        this.orderApply = orderApply;
    }

    public String getOrderAll() {
        return orderAll;
    }

    public void setOrderAll(String orderAll) {
        this.orderAll = orderAll;
    }
}
