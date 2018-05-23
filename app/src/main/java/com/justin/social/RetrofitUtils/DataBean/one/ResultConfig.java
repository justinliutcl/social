package com.justin.social.RetrofitUtils.DataBean.one;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.RetrofitUtils.configRequest.CallBack;
import com.justin.social.activity.OrderDetialActivity;
import com.justin.social.alipay2.AliPayUse;
import com.justin.social.utils.AppUtils;
import com.justin.social.utils.ContentKey;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/4/10.
 */

public class ResultConfig extends BaseConfig {

    public ResultConfig data;
    public String type;
    public String orderNum;
    public String allCharge;

    public ResultConfig getData() {
        return data;
    }

    public void setData(ResultConfig data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getAllCharge() {
        return allCharge;
    }

    public void setAllCharge(String allCharge) {
        this.allCharge = allCharge;
    }
}
