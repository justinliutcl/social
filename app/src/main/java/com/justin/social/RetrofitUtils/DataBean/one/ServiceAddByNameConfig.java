package com.justin.social.RetrofitUtils.DataBean.one;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.view.View;

import com.justin.social.R;
import com.justin.social.model.base.BaseModel;

import java.util.List;

/**
 * Created by ASUS on 2018/4/17.
 */

public class ServiceAddByNameConfig extends BaseModel {
    private String id;
    private String serviceName;
    private String serviceCharge;
    private String serviceInfo;
    private String status;
    private ServiceAddConfig data;

    public static int NORMAL_IMAGE = R.drawable.icon_pay_normal;
    public static int SELECT_IMAGE = R.drawable.icon_pay_select;
    public ObservableBoolean isSelect;

    public ServiceAddByNameConfig(Context context) {
        super(context);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getServiceInfo() {
        return serviceInfo;
    }

    public void setServiceInfo(String serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ServiceAddConfig getData() {
        return data;
    }

    public void setData(ServiceAddConfig data) {
        this.data = data;
    }

    public void onClick(View view){
        isSelect.set(!isSelect.get());
    }
}
