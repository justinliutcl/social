package com.justin.social.model.one;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.four.SocialTool;
import com.justin.social.RetrofitUtils.DataBean.one.CityConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.activity.WriteSocialNoteActivity;
import com.justin.social.adapter.SocialToolNormalAdapter;
import com.justin.social.databinding.ActivityCustomSocialaccuBinding;
import com.justin.social.databinding.ActivitySocialCalculaterBinding;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/4/14.
 */

public class CustomSocialAccuModel extends BaseModel {
    ActivityCustomSocialaccuBinding binding;
    public ObservableField<String> city;
    public ObservableField<String> hourseType;
    public ObservableField<String> baseType;
    public ObservableField<String> accuBbaseType;
    public ObservableField<String> socialSecurity;
    public ObservableField<String> accuSecurity;
    public ObservableField<String> title;
    public ObservableField<String> oldMoney;
    public ObservableField<String> medicalMoney;
    public ObservableField<String> unEmpolymentMoney;
    public ObservableField<String> borthMoney;
    public ObservableField<String> industyMoney;
    public ObservableBoolean isSocial;
    CityConfig cityConfig;
    HttpConfigManager manager;
    private String min;
    private String minAccu;

    public CustomSocialAccuModel(Context context) {
        super(context);
        city = new ObservableField<>("北京");
        baseType = new ObservableField<>("0.00-0.00");
        accuBbaseType = new ObservableField<>("0.00-0.00");
        socialSecurity = new ObservableField<>("0.00");
        accuSecurity = new ObservableField<>("0.00");
        title = new ObservableField<>("我的社保");
        oldMoney            = new ObservableField<>("0.00");
        medicalMoney        = new ObservableField<>("0.00");
        unEmpolymentMoney   = new ObservableField<>("0.00");
        borthMoney          = new ObservableField<>("0.00");
        industyMoney        = new ObservableField<>("0.00");
        isSocial        = new ObservableBoolean(true);
        manager = new HttpConfigManager();
        hourseType = new ObservableField<>(mContext.getResources().getString(R.string.hourse_type_local_city));
    }

    public void setSocial(boolean isSocial){
        this.isSocial.set(isSocial);
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
        minAccu = cityConfig.getData().get(0).getAccumulationFundBaseLow();
        getAllMoneyMessage();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_tv:
                if(isSocial.get()){
                    WriteSocialNoteActivity.JumpWriteSocial(mContext,0);
                }else{
                    WriteSocialNoteActivity.JumpWriteSocial(mContext,1);
                }
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
            case R.id.hourse_type:
                DialogUtils.getDialogUtilInstance().showHourseTypeDialog(mContext, new DialogUtils.ItemClickBack() {
                    @Override
                    public void onBack(String s) {
                        if(!hourseType.get().equals(s)){
                            hourseType.set(s);
                            getAllMoneyMessage();
                        }
                        DialogUtils.getDialogUtilInstance().dismiss();
                    }
                });
                break;
        }
    }


    private void getAllMoneyMessage() {
        new HttpConfigManager().getToolMoneyConfig( min, minAccu,hourseType.get(),city.get(), new BeanConfigCallBack<SocialTool>() {
            @Override
            public void onDataResponse(SocialTool bean) {
                if (bean == null || bean.getData() == null)
                    return;
                socialSecurity.set(bean.getData().getSocialSecurity());
                accuSecurity.set(bean.getData().getAccumulation());
                List<SocialTool.SocialItem> list = new ArrayList<>();
                SocialTool.SocialItem item = new SocialTool.SocialItem();
                item.setTitle("类别");
                item.setBase("基数");
                item.setSingle("个人");
                item.setCompany("公司");
                item.setSum("总计");
                list.add(item);
                bean.getData().getEndowmentInsurance().setTitle("养老");
                bean.getData().getMedicalInsurance().setTitle("医疗");
                bean.getData().getUnemploymentInsurance().setTitle("失业");
                bean.getData().getEmploymentInjuryInsurance().setTitle("工伤");
                bean.getData().getMaternityInsurance().setTitle("生育");
                bean.getData().getAccumulationFund().setTitle("公积金");

                bean.getData().getSum().setTitle("总计");
                list.add(bean.getData().getEndowmentInsurance());
                list.add(bean.getData().getMedicalInsurance());
                list.add(bean.getData().getUnemploymentInsurance());
                list.add(bean.getData().getEmploymentInjuryInsurance());
                list.add(bean.getData().getMaternityInsurance());
                list.add(bean.getData().getAccumulationFund());
                list.add(bean.getData().getSum());

                oldMoney.set(bean.getData().getEndowmentInsurance().getSum());
                medicalMoney.set(bean.getData().getMedicalInsurance().getSum());
                unEmpolymentMoney.set(bean.getData().getUnemploymentInsurance().getSum());
                borthMoney.set(bean.getData().getMaternityInsurance().getSum());
                industyMoney.set(bean.getData().getEmploymentInjuryInsurance().getSum());
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
                    minAccu = cityConfig.getData().get(index).getAccumulationFundBaseLow();
                    getAllMoneyMessage();
                }
            }
        }
    };

    public void initBind(ActivityCustomSocialaccuBinding binding) {
        this.binding = binding;
    }
}
