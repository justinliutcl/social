package com.justin.social.model.one;

import android.content.Context;
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
import com.justin.social.databinding.ActivitySocialSearchBinding;
import com.justin.social.databinding.ActivityWriteSocialNoteBinding;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.AppUtils;
import com.justin.social.utils.DialogUtils;

/**
 * Created by ASUS on 2018/3/31.
 */

public class SocialSearchModel extends BaseModel {
    String phone;
    private ActivitySocialSearchBinding bind;

    public String customURL = "http://www.youxuanzhijia.top";
    public String chinaURL = "http://www.qgsbcx.com";
    public String beijingURL = "http://www.bjrbj.com";

    public SocialSearchModel(Context context) {
        super(context);
        manager = new HttpConfigManager();
        CommonSettingValue value = CommonSettingValue.getIns(mContext);
        phone = value.getCurrentPhone();
        city = new ObservableField<>(value.getCity(phone));
    }

    HttpConfigManager manager;
    CityConfig cityConfig;
    public ObservableField<String> city;
    public DbUser user;


    public void setData(final ActivitySocialSearchBinding bind) {
        this.bind = bind;
        UserDataObtain.getInstance(mContext).getUserFromPhoneRxJava(phone, new IDataObtain.IDBResCallback<DbUser>() {
            @Override
            public void complete(DbUser dbUser) {
                user = dbUser;
                if (dbUser.insuredCity != null) {
                    city.set(dbUser.insuredCity);
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
                }
            }
        });
    }

    private DialogUtils.ItemClickBack cityCall = new DialogUtils.ItemClickBack() {
        @Override
        public void onBack(String s) {
            DialogUtils.getDialogUtilInstance().dismiss();
            if (!city.get().equals(s)) {
                city.set(s);
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
        }
    }

    public void onCopyClick(View view) {
        switch (view.getId()) {
            case R.id.custom_copy:
                AppUtils.copyToClip(customURL, mContext);
                break;
            case R.id.china_copy:
                AppUtils.copyToClip(chinaURL, mContext);
                break;
            case R.id.beijing_copy:
                AppUtils.copyToClip(beijingURL, mContext);
                break;
        }
    }

    public void jumpClick(View view) {
        switch (view.getId()) {
            case R.id.custom_jump:
                AppUtils.jumpToUrl(customURL, mContext);
                break;
            case R.id.china_jump:
                AppUtils.jumpToUrl(chinaURL, mContext);
                break;
            case R.id.beijing_jump:
                AppUtils.jumpToUrl(beijingURL, mContext);
                break;
        }
    }
}
