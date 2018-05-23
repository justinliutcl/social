package com.justin.social.model.one;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.one.HosptialConfig;
import com.justin.social.RetrofitUtils.DataBean.one.NewListBean;
import com.justin.social.RetrofitUtils.DataBean.one.NewsListConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.adapter.NewsListContentAdapter;
import com.justin.social.databinding.FragmentMessageHospBinding;
import com.justin.social.databinding.FragmentNewslistOneBinding;
import com.justin.social.model.base.BaseModel;

import java.util.List;

/**
 * Created by ASUS on 2018/4/19.
 */

public class MessageHospModel extends BaseModel {
    HttpConfigManager httpConfigManager;
    FragmentMessageHospBinding mBinding;
    public ObservableField<String> hosp1;
    public ObservableField<String> hosp2;
    public ObservableField<String> hosp3;
    public ObservableField<String> hosp4;
    public ObservableField<String> hosp5;

    public MessageHospModel(Context context) {
        super(context);
        httpConfigManager = new HttpConfigManager();
        hosp1 = new ObservableField<>("");
        hosp2 = new ObservableField<>("");
        hosp3 = new ObservableField<>("");
        hosp4 = new ObservableField<>("");
        hosp5 = new ObservableField<>("");
    }

    public void initNewsList() {
        new HttpConfigManager().getUserHospConfig(CommonSettingValue.getIns(mContext).getCurrentUserId(), new BeanConfigCallBack<HosptialConfig>() {
            @Override
            public void onDataResponse(HosptialConfig bean) {
                if(bean!=null&&bean.getData()!=null){
                    hosp1.set(bean.getData().getHospitalOne());
                    hosp2.set(bean.getData().getHospitalTwo());
                    hosp3.set(bean.getData().getHospitalThree());
                    hosp4.set(bean.getData().getHospitalFour());
                    hosp5.set(bean.getData().getHospitalFive());
                }
            }
        });
    }

    public void init(FragmentMessageHospBinding mBinding) {
        this.mBinding = mBinding;
    }

    public void onClick(View view) {
        new HttpConfigManager().saveUserHospConfig(CommonSettingValue.getIns(mContext).getCurrentUserId(),
                mBinding.hosp1Text.getText().toString(),
                mBinding.hosp2Text.getText().toString(),
                mBinding.hosp3Text.getText().toString(),
                mBinding.hosp4Text.getText().toString(),
                mBinding.hosp5Text.getText().toString(), new BeanConfigCallBack<BaseConfig>() {
                    @Override
                    public void onDataResponse(BaseConfig bean) {
                        toastShow("上传成功");
                    }
                });
    }
}
