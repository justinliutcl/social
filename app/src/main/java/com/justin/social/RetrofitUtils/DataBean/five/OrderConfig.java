package com.justin.social.RetrofitUtils.DataBean.five;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.TextView;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.utils.AppUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/4/10.
 */

public class OrderConfig extends BaseConfig{
    public static final String HAVE_PAY     = "1";
    public static final String NO_PAY       = "0";
    public static final String DURPAY       = "-1";
    public static final String ALL_PAY      = "-2";

    public List<OrderConfig>data;
    public String orderNum;
    public String createTime;
    public String insuredTime;
    public String allCharge;
    public String status;
    public String type;
    public String orderStatus;

    public List<OrderConfig> getData() {
        return data;
    }

    public void setData(List<OrderConfig> data) {
        this.data = data;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getInsuredTime() {
        return insuredTime;
    }

    public void setInsuredTime(String insuredTime) {
        this.insuredTime = insuredTime;
    }

    public String getAllCharge() {
        return allCharge;
    }

    public void setAllCharge(String allCharge) {
        this.allCharge = allCharge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void onItemClick(View view){}

    @BindingAdapter("createTime")
    public static void changeCreate(TextView text,String time){
        text.setText("下单日期: "+AppUtils.getTime("yyyy-MM-dd  hh:mm:ss",Long.parseLong(time)));
    }
}
