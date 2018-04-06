package com.justin.social.model.one;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.justin.social.RXDbUtils.DB.UserDataObtain;
import com.justin.social.RXDbUtils.DBbean.DbUser;
import com.justin.social.RXDbUtils.DBbean.IDataObtain;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.one.CityConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.activity.OrderTableActivity;
import com.justin.social.databinding.ActivityWriteSocialNoteBinding;
import com.justin.social.model.base.BaseModel;

/**
 * Created by ASUS on 2018/3/31.
 */

public class WritePeopleModel extends BaseModel {
    public WritePeopleModel(Context context) {
        super(context);
        manager = new HttpConfigManager();
    }
    HttpConfigManager manager;
    CityConfig cityConfig;
    private String min;
    private String max;
    public boolean isAccu;
    public void onNextClick(View view){
        if(max == null||min == null){
            toastShow("请检查网络");
            return;
        }
        OrderTableActivity.JumpToOrder(mContext,isAccu,min,max);
    }

    public void setData(final ActivityWriteSocialNoteBinding bind) {
        final String phone = CommonSettingValue.getIns(mContext).getCurrentPhone();
        UserDataObtain.getInstance(mContext).getUserFromPhoneRxJava(phone, new IDataObtain.IDBResCallback<DbUser>() {
            @Override
            public void complete(DbUser dbUser) {
                if (dbUser.userName != null) {
                    bind.name.setText(dbUser.userName);
                }
                if (dbUser.householdType != null) {
                    bind.registType.setText(dbUser.householdType);
                }
                if (dbUser.insuredCity != null) {
                    bind.city.setText(dbUser.insuredCity);
                }
                bind.phone.setText(phone);
            }
        });
    }

    public void getCity() {
        manager.getCityListConfig(new BeanConfigCallBack<CityConfig>() {
            @Override
            public void onDataResponse(CityConfig bean) {
                if(bean.getData()!=null&&!bean.getData().isEmpty()){
                    cityConfig = CommonSettingValue.getIns(mContext).getCity();
                    cityConfig = bean;
                    CommonSettingValue.getIns(mContext).setCity(bean);
                    if(isAccu){
                        min = cityConfig.getData().get(0).getAccumulationFundBaseLow();
                        max = cityConfig.getData().get(0).getAccumulationFundBaseHigh();
                    }else{
                        min = cityConfig.getData().get(0).getSocialSecurityBaseLow();
                        max = cityConfig.getData().get(0).getSocialSecurityBaseHigh();
                    }
                }
            }
        });
    }

    public void onShowDialogClick(View view){
        switch (view.getId()){

        }
    }
}
