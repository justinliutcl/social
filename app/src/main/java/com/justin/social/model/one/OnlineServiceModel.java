package com.justin.social.model.one;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.justin.social.R;
import com.justin.social.RXDbUtils.DB.UserDataObtain;
import com.justin.social.RXDbUtils.DBbean.DbUser;
import com.justin.social.RXDbUtils.DBbean.IDataObtain;
import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.one.OnlineServiceConfig;
import com.justin.social.RetrofitUtils.DataBean.one.SocialMoneyConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.activity.SocialPayActivity;
import com.justin.social.databinding.ActivityOnlineServiceBinding;
import com.justin.social.databinding.ActivityOrderTableBinding;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.AppUtils;
import com.justin.social.utils.BankUtils;
import com.justin.social.utils.DialogUtils;

/**
 * Created by ASUS on 2018/3/31.
 */

public class OnlineServiceModel extends BaseModel {

    private ActivityOnlineServiceBinding bind;

    public ObservableField<String> image1;
    public ObservableField<String> name1;
    public ObservableField<String> mes1;
    public ObservableField<String> image2;
    public ObservableField<String> name2;
    public ObservableField<String> mes2;
    public ObservableField<String> phone;
    public ObservableField<String> email;
    public ObservableField<String> local;

    public String qq1;
    public String qq2;

    HttpConfigManager manager;

    public OnlineServiceModel(Context context) {
        super(context);
        image1 = new ObservableField<>("");
        name1 = new ObservableField<>("");
        mes1 = new ObservableField<>("");
        image2 = new ObservableField<>("");
        name2 = new ObservableField<>("");
        mes2 = new ObservableField<>("");
        phone = new ObservableField<>("");
        email = new ObservableField<>("");
        local = new ObservableField<>("");
        manager = new HttpConfigManager();
    }

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.online1:
                AppUtils.JumpToQQ(mContext, qq1);
                break;
            case R.id.online2:
                AppUtils.JumpToQQ(mContext, qq2);
                break;
            case R.id.phone_call:
                AppUtils.diallPhone(phone.get(), mContext);
                break;
        }
    }

    public void initData() {
        manager.getOnlineServiceConfig(new BeanConfigCallBack<OnlineServiceConfig>() {
            @Override
            public void onDataResponse(OnlineServiceConfig bean) {
                if(bean!=null){
                    if(bean.getData().size()>0){
                        OnlineServiceConfig c1 = bean.getData().get(0);
                        OnlineServiceConfig c2 = bean.getData().get(1);
                        image1.set(c1.getHeadImg());
                        name1.set(c1.getCustomServiceName());
                        mes1.set(c1.getCustomServiceType());
                        qq1 = c1.getQq();
                        image2.set(c2.getHeadImg());
                        name2.set(c2.getCustomServiceName());
                        mes2.set(c2.getCustomServiceType());
                        qq2 = c2.getQq();
                    }
                }
            }
        });
    }
}
