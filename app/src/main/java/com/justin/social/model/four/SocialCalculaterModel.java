package com.justin.social.model.four;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.ScrollView;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.four.SocialTool;
import com.justin.social.RetrofitUtils.DataBean.one.CityConfig;
import com.justin.social.RetrofitUtils.DataBean.one.SocialMoneyConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.activity.OnlineServiceActivity;
import com.justin.social.adapter.SocialToolNormalAdapter;
import com.justin.social.databinding.ActivitySocialCalculaterBinding;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.AppUtils;
import com.justin.social.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/4/14.
 */

public class SocialCalculaterModel extends BaseModel {
    ActivitySocialCalculaterBinding binding;
    SocialToolNormalAdapter adapter;
    public ObservableField<String> city;
    public ObservableField<String> hourseType;
    public ObservableField<String> baseType;
    public ObservableField<String> accuBbaseType;
    public ObservableField<String> socialSecurity;
    public ObservableField<String> accuSecurity;
    public ObservableField<String> sum;
    public ObservableBoolean isShowDetial;
    CityConfig cityConfig;
    HttpConfigManager manager;
    private String min;
    private String max;
    private String minAccu;
    private String maxAccu;
    private String text;
    private String accuText;

    public SocialCalculaterModel(Context context) {
        super(context);
        city = new ObservableField<>("北京");
        baseType = new ObservableField<>("0.00-0.00");
        accuBbaseType = new ObservableField<>("0.00-0.00");
        socialSecurity = new ObservableField<>("0.00");
        accuSecurity = new ObservableField<>("0.00");
        sum = new ObservableField<>("0.00");
        isShowDetial = new ObservableBoolean(false);
        manager = new HttpConfigManager();
        hourseType = new ObservableField<>(mContext.getResources().getString(R.string.hourse_type_local_city));
    }

    public void getCity() {
        cityConfig = CommonSettingValue.getIns(mContext).getCity();
        if (cityConfig != null) {
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

    public void initCityConfig() {
        min = cityConfig.getData().get(0).getSocialSecurityBaseLow();
        max = cityConfig.getData().get(0).getSocialSecurityBaseHigh();
        minAccu = cityConfig.getData().get(0).getAccumulationFundBaseLow();
        maxAccu = cityConfig.getData().get(0).getAccumulationFundBaseHigh();
        baseType.set(min + " - " + max);
        accuBbaseType.set(minAccu + " - " + maxAccu);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.social_min:
                binding.baseText.setText(min);
                break;
            case R.id.accu_min:
                binding.fiveAccuText.setText(minAccu);
                break;
            case R.id.online_service:
                mContext.startActivity(new Intent(mContext, OnlineServiceActivity.class));
                break;
            case R.id.call_phone:
                if (CommonSettingValue.getIns(mContext).getCustomPhone() != null) {
                    AppUtils.diallPhone(CommonSettingValue.getIns(mContext).getCustomPhone(), mContext);
                } else {

                }
                break;
            case R.id.detial_ll:
                if (isShowDetial.get()) {
                    isShowDetial.set(false);
                    binding.detialTv.setText("展开缴费明细");
                } else {
                    isShowDetial.set(true);
                    binding.detialTv.setText("收起缴费明细");

                }
                if (adapter != null) {
                    binding.scrollView.smoothScrollBy(0, 50);
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
                        hourseType.set(s);
                        DialogUtils.getDialogUtilInstance().dismiss();
                    }
                });
                break;
        }
    }

    public void onNextClick(View view) {
        text = binding.baseText.getText().toString();
        accuText = binding.fiveAccuText.getText().toString();
        if (text.isEmpty() || accuText.isEmpty() || !(Double.parseDouble(text) >= Double.parseDouble(min) && Double.parseDouble(text) <= Double.parseDouble(max))) {
            toastShow("请输入正确社保金额");
            return;
        }
        if (text.isEmpty() || !(Double.parseDouble(accuText) >= Double.parseDouble(minAccu) &&
                Double.parseDouble(accuText) <= Double.parseDouble(maxAccu))) {
            toastShow("请输入正确社保金额");
            return;
        }
        getAllMoneyMessage();
    }

    private void getAllMoneyMessage() {
        new HttpConfigManager().getToolMoneyConfig(text, accuText, hourseType.get(), city.get(), new BeanConfigCallBack<SocialTool>() {
            @Override
            public void onDataResponse(SocialTool bean) {
                if (bean == null || bean.getData() == null)
                    return;
                socialSecurity.set(bean.getData().getSocialSecurity());
                accuSecurity.set(bean.getData().getAccumulation());
                sum.set(bean.getData().getSumAll());
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
                adapter = new SocialToolNormalAdapter(list, mContext, null);
                binding.list.setAdapter(adapter);
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
                    accuBbaseType.set(minAccu + " - " + maxAccu);
                }
            }
        }
    };

    public void initBind(ActivitySocialCalculaterBinding binding) {
        this.binding = binding;
    }
}
