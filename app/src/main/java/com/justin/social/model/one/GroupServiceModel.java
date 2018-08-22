package com.justin.social.model.one;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.one.PoliceConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.activity.GroupServiceActivity;
import com.justin.social.activity.OnlineServiceActivity;
import com.justin.social.activity.PoliceDetialActivity;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.AppUtils;

/**
 * Created by ASUS on 2018/5/7.
 */

public class GroupServiceModel extends BaseModel {
    public ObservableField<String>mes;
    public GroupServiceModel(Context context) {
        super(context);
        mes = new ObservableField<>("");
        initData();
    }

    public void onServiceClick(View view){
        switch (view.getId()){
            case R.id.online_service_ll:
                mContext.startActivity(new Intent(mContext,OnlineServiceActivity.class));
                break;
            case R.id.phone_service_ll:
                if(CommonSettingValue.getIns(mContext).getCustomPhone()!=null){
                    AppUtils.diallPhone(CommonSettingValue.getIns(mContext).getCustomPhone(),mContext);
                }else{

                }
                break;
        }
    }

    public void initData(){
        new HttpConfigManager().getPoliceDetialConfig(PoliceDetialActivity.GROUP_TYPE, new BeanConfigCallBack<PoliceConfig>() {
            @Override
            public void onDataResponse(PoliceConfig bean) {
                mes.set(bean.getData().getContent());
            }
        });
    }
}
