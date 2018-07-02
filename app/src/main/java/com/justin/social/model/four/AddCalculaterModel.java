package com.justin.social.model.four;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.five.HeaderImageConfig;
import com.justin.social.RetrofitUtils.DataBean.four.SocialTool;
import com.justin.social.RetrofitUtils.DataBean.one.CityConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.adapter.SocialToolNormalAdapter;
import com.justin.social.databinding.ActivityAddCalculaterBinding;
import com.justin.social.databinding.ActivitySocialCalculaterBinding;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/4/14.
 */

public class AddCalculaterModel extends BaseModel {
    ActivityAddCalculaterBinding binding;
    public ObservableField<String> city;
    public ObservableField<String> baseType;
    public ObservableField<String> socialSecurity;
    public ObservableField<String> sum;
    CityConfig cityConfig;
    HttpConfigManager manager;
    private String min;
    private String max;
    private String minAccu;
    private String maxAccu;
    private String text;
    private String monthTime;

    public AddCalculaterModel(Context context) {
        super(context);
        city = new ObservableField<>("北京");
        baseType = new ObservableField<>("0.00-0.00");
        socialSecurity = new ObservableField<>("0.00");
        sum = new ObservableField<>("0.00");
        manager = new HttpConfigManager();
    }

    public void getCity() {
        cityConfig = CommonSettingValue.getIns(mContext).getCity();
        if(cityConfig!=null){
            initCityConfig();
        }
        manager.getCityListConfig(new BeanConfigCallBack<CityConfig>() {
            @Override
            public void onDataResponse(CityConfig bean) {
                if (bean.getData() != null && !bean.getData().isEmpty()) {
                    cityConfig = bean;
                    CommonSettingValue.getIns(mContext).setCity(bean);
                    initCityConfig();
                }
            }
        });
    }

    public void initCityConfig(){
        min = cityConfig.getData().get(0).getSocialSecurityBaseLow();
        max = cityConfig.getData().get(0).getSocialSecurityBaseHigh();
        minAccu = cityConfig.getData().get(0).getAccumulationFundBaseLow();
        maxAccu = cityConfig.getData().get(0).getAccumulationFundBaseHigh();
        baseType.set(min + " - " + max);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.social_min:
                    binding.baseText.setText(min);
                break;
            case R.id.accu_min:
                break;
            case R.id.detial_ll:
                break;
        }
    }

    public void onDialogShow(View view) {
        switch (view.getId()) {
            case R.id.social_type_ll:
                if (cityConfig != null) {
                    DialogUtils.getDialogUtilInstance().showCityDialog(mContext, cityConfig.getStringListCity(), cityCall);

                }
                break;
        }
    }

    public void onNextClick(View view) {
        text = binding.baseText.getText().toString();
        monthTime = binding.monthNum.getText().toString();
        if (text.isEmpty()  || !(Double.parseDouble(text) >= Double.parseDouble(min) && Double.parseDouble(text) <= Double.parseDouble(max))) {
            toastShow("请输入正确社保金额");
            return;
        }
        if (monthTime.isEmpty() || ((int)Double.parseDouble(monthTime))<1) {
            toastShow("请输入正确补缴时间");
            return;
        }
        getAllMoneyMessage();
    }

    private void getAllMoneyMessage() {
        int time = (int) (Double.parseDouble(monthTime) * 30);
        new HttpConfigManager().getAddCalculaterConfig(city.get(), String.valueOf(time),text, new BeanConfigCallBack<HeaderImageConfig>() {
            @Override
            public void onDataResponse(HeaderImageConfig bean) {
                if (bean == null || bean.getData() == null)
                    return;
                sum.set(bean.getData());
            }
        });
    }

    private DialogUtils.ItemClickBack cityCall = new DialogUtils.ItemClickBack() {
        @Override
        public void onBack(String s) {
            DialogUtils.getDialogUtilInstance().dismiss();
            if (!city.get().equals(s)) {
                int index = cityConfig.getIndex(s);
                if (index >= 0) {
                    min = cityConfig.getData().get(index).getSocialSecurityBaseLow();
                    max = cityConfig.getData().get(index).getSocialSecurityBaseHigh();
                    minAccu = cityConfig.getData().get(index).getAccumulationFundBaseLow();
                    maxAccu = cityConfig.getData().get(index).getAccumulationFundBaseHigh();
                    baseType.set(min + " - " + max);
                }
            }
        }
    };

    public void initBind(ActivityAddCalculaterBinding binding) {
        this.binding = binding;
    }
}
