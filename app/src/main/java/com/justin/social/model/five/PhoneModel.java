package com.justin.social.model.five;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;

import com.justin.social.R;
import com.justin.social.RXDbUtils.DB.UserDataObtain;
import com.justin.social.RXDbUtils.DBbean.DbUser;
import com.justin.social.RXDbUtils.DBbean.IDataObtain;
import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.five.UserInfoConfig;
import com.justin.social.RetrofitUtils.DataBean.one.CityConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.databinding.ActivityUserMessageBinding;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.AppUtils;
import com.justin.social.utils.DialogUtils;

/**
 * Created by ASUS on 2018/4/19.
 */

public class PhoneModel  {

    public String name;
    public String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public PhoneModel(String name, String phone) {
        super();
        this.name = name;
        this.phone = phone;
    }

    public void onClick(View view) {
        AppUtils.doSendSMSTo(view.getContext(),phone,"快叫上小伙伴们一起来缴社保及存档案哦！http://www.youxuanzhijia.top");
    }



}
