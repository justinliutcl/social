package com.justin.social.model.one;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;

import com.justin.social.R;
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
import com.justin.social.utils.DialogUtils;

import java.util.List;

/**
 * Created by ASUS on 2018/3/31.
 */

public class WritePeopleModel extends BaseModel {
    String phone;
    private ActivityWriteSocialNoteBinding bind;

    public WritePeopleModel(Context context) {
        super(context);
        manager = new HttpConfigManager();
        CommonSettingValue value = CommonSettingValue.getIns(mContext);
        phone = value.getCurrentPhone();
        city = new ObservableField<>(value.getCity(phone));
        hourseType = new ObservableField<>(value.getHourseType(phone));
    }

    HttpConfigManager manager;
    CityConfig cityConfig;
    private String min;
    private String max;
    private String minAccu;
    private String maxAccu;
    public boolean isAccu;
    public int type;
    public ObservableField<String> city;
    public ObservableField<String> hourseType;
    public DbUser user;

    public void onNextClick(View view) {
        if (max == null || min == null) {
            toastShow("请检查网络");
            return;
        }
        user.setIdCard(bind.idcard.getText().toString());
        user.setUserName(bind.name.getText().toString());
        user.setHouseholdType(hourseType.get());
        user.setInsuredCity(city.get());
        UserDataObtain.getInstance(mContext).updataUser(user, new IDataObtain.IDBResCallback<DbUser>() {
            @Override
            public void complete(DbUser dbUser) {
            }
        });
        if (type == 2) {
            OrderTableActivity.JumpToOrder(mContext, type, min, max, minAccu, maxAccu, city.get(), hourseType.get());
        } else {
            OrderTableActivity.JumpToOrder(mContext, type, min, max, city.get(), hourseType.get());
        }

    }

    public void setData(final ActivityWriteSocialNoteBinding bind) {
        this.bind = bind;
        UserDataObtain.getInstance(mContext).getUserFromPhoneRxJava(phone, new IDataObtain.IDBResCallback<DbUser>() {
            @Override
            public void complete(DbUser dbUser) {
                user = dbUser;
                if (dbUser.userName != null) {
                    bind.name.setText(dbUser.userName);
                }
                if (dbUser.householdType != null) {
                    hourseType.set(dbUser.householdType);
                }
                if (dbUser.insuredCity != null) {
                    city.set(dbUser.insuredCity);
                }
                if (dbUser.idCard != null) {
                    bind.idcard.setText(dbUser.idCard);
                }

            }
        });
    }

    public void getCity() {
        cityConfig = CommonSettingValue.getIns(mContext).getCity();

        manager.getCityListConfig(new BeanConfigCallBack<CityConfig>() {
            @Override
            public void onDataResponse(CityConfig bean) {
                if (bean.getData() != null && !bean.getData().isEmpty()) {
                    cityConfig = bean;
                    CommonSettingValue.getIns(mContext).setCity(bean);
                    if (type == 2) {
                        min = cityConfig.getData().get(0).getSocialSecurityBaseLow();
                        max = cityConfig.getData().get(0).getSocialSecurityBaseHigh();
                        minAccu = cityConfig.getData().get(0).getAccumulationFundBaseLow();
                        maxAccu = cityConfig.getData().get(0).getAccumulationFundBaseHigh();
                    } else {
                        if (isAccu) {
                            min = cityConfig.getData().get(0).getAccumulationFundBaseLow();
                            max = cityConfig.getData().get(0).getAccumulationFundBaseHigh();
                        } else {
                            min = cityConfig.getData().get(0).getSocialSecurityBaseLow();
                            max = cityConfig.getData().get(0).getSocialSecurityBaseHigh();
                        }
                    }
                }
            }
        });
    }

    private DialogUtils.ItemClickBack cityCall = new DialogUtils.ItemClickBack() {
        @Override
        public void onBack(String s) {
            DialogUtils.getDialogUtilInstance().dismiss();
            if (!city.get().equals(s)) {
                if (cityConfig.getIndex(s) >= 0) {
                    if (type == 2) {
                        min = cityConfig.getData().get(0).getSocialSecurityBaseLow();
                        max = cityConfig.getData().get(0).getSocialSecurityBaseHigh();
                        minAccu = cityConfig.getData().get(0).getAccumulationFundBaseLow();
                        maxAccu = cityConfig.getData().get(0).getAccumulationFundBaseHigh();
                    } else {
                        if (isAccu) {
                            min = cityConfig.getData().get(0).getAccumulationFundBaseLow();
                            max = cityConfig.getData().get(0).getAccumulationFundBaseHigh();
                        } else {
                            min = cityConfig.getData().get(0).getSocialSecurityBaseLow();
                            max = cityConfig.getData().get(0).getSocialSecurityBaseHigh();
                        }
                    }
                }

            }
        }
    };

    public void onShowDialogClick(View view) {
        switch (view.getId()) {
            case R.id.city_ll:
                if (cityConfig != null) {
                    DialogUtils.getDialogUtilInstance().showCityDialog(mContext, cityConfig.getStringListCity(), cityCall);

                }
                break;
            case R.id.regist_type_ll:
                DialogUtils.getDialogUtilInstance().showHourseTypeDialog(mContext, new DialogUtils.ItemClickBack() {
                    @Override
                    public void onBack(String s) {
                        hourseType.set(s);
                        DialogUtils.getDialogUtilInstance().dismiss();
                    }
                });
                break;
        }
    }
}
