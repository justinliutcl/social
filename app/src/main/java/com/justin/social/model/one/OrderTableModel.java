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
import com.justin.social.RetrofitUtils.DataBean.one.SocialMoneyConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.activity.SocialPayActivity;
import com.justin.social.databinding.ActivityOrderTableBinding;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.AppUtils;
import com.justin.social.utils.DialogUtils;

/**
 * Created by ASUS on 2018/3/31.
 */

public class OrderTableModel extends BaseModel {
    public ObservableBoolean isSocial;
    public ObservableBoolean isAccu;
    public ObservableBoolean isFive;
    public ObservableField<String> baseType;
    public ObservableField<String> baseFiveType;
    public ObservableField<String> section;
    public ObservableField<String> fiveSection;
    public ObservableField<String> appDur;
    public ObservableField<String> tableType;

    public ObservableField<String> socialSecurity;
    public ObservableField<String> accuSecurity;
    public ObservableField<String> personalTax;
    public ObservableField<String> disabilityInsurance;
    public ObservableField<String> serviceCharge;
    public ObservableField<String> overdue_fine;
    public ObservableField<String> sum;

    private ActivityOrderTableBinding bind;
    private double minBase;
    private double maxBase;
    private double minFiveBase;
    private double maxFiveBase;
    private String cityName;
    private String hourseType;
    public String currentTime;
    public String defaultBase;
    public String defaultFiveBase;
    public ObservableBoolean isFirst;

    public OrderTableModel(Context context) {
        super(context);
        isAccu = new ObservableBoolean(false);
        isSocial = new ObservableBoolean(false);
        isFirst = new ObservableBoolean(false);
        isFive = new ObservableBoolean(false);
        baseType = new ObservableField<>("");
        baseFiveType = new ObservableField<>("");
        section = new ObservableField<>("");
        fiveSection = new ObservableField<>("");
        appDur = new ObservableField<>("月付");

        socialSecurity = new ObservableField<>("0.00");
        accuSecurity = new ObservableField<>("0.00");
        personalTax = new ObservableField<>("0.00");
        disabilityInsurance = new ObservableField<>("0.00");
        serviceCharge = new ObservableField<>("0.00");
        overdue_fine = new ObservableField<>("0.00");
        sum = new ObservableField<>("0.00");

        tableType = new ObservableField<>(mContext.getString(R.string.social_type_have));
        currentTime = AppUtils.getTime("yyyy-MM-dd");
    }

    public void onNextClick(View view) {
        String text = bind.baseText.getText().toString();
        if (text.isEmpty() || !(Double.parseDouble(text) >= minBase && Double.parseDouble(text) <= maxBase)) {
            toastShow("请输入正确金额");
            return;
        }
        if (Double.parseDouble(sum.get()) < 100) {
            toastShow("请等待服务器返回支付金额");
            return;
        }
        sendOrder();
        mContext.startActivity(new Intent(mContext, SocialPayActivity.class));
    }

    public void initType(boolean isAcc, String min, String max, String city, String hourseType) {
        isAccu.set(isAcc);
        isSocial.set(!isAcc);
        baseType.set(isAcc ? "公积金基数" : "社保基数");
        this.section.set(min + " - " + max);
        minBase = Double.parseDouble(min);
        maxBase = Double.parseDouble(max);
        cityName = city;
        this.hourseType = hourseType;
        CommonSettingValue value = CommonSettingValue.getIns(mContext);
        if (isAcc) {
            String base = value.getAccuBase(value.getCurrentPhone());
            defaultBase = base == null ? String.valueOf(minBase) : base;
            getAccuMoneyMessage(String.valueOf(defaultBase));
        } else {
            String base = value.getSocialBase(value.getCurrentPhone());
            defaultBase = base == null ? String.valueOf(minBase) : base;
            getMoneyMessage(String.valueOf(defaultBase));
        }

    }

    public void initType(String min, String max, String minAccu, String maxAccu, String city, String hourseType) {
        this.isFive.set(true);
        isAccu.set(true);
        isSocial.set(true);
        baseType.set("社保基数");
        baseFiveType.set("公积金基数");
        this.section.set(min + " - " + max);
        this.fiveSection.set(minAccu + " - " + maxAccu);
        minBase = Double.parseDouble(min);
        maxBase = Double.parseDouble(max);
        minFiveBase = Double.parseDouble(minAccu);
        maxFiveBase = Double.parseDouble(maxAccu);
        cityName = city;
        this.hourseType = hourseType;
        CommonSettingValue value = CommonSettingValue.getIns(mContext);
        String base = value.getAccuBase(value.getCurrentPhone());
        defaultFiveBase = base == null ? String.valueOf(minFiveBase) : base;

        String base2 = value.getSocialBase(value.getCurrentPhone());
        defaultBase = base2 == null ? String.valueOf(minBase) : base2;

        getAllMoneyMessage(String.valueOf(defaultBase),String.valueOf(defaultFiveBase));
    }

    public void initBind(final ActivityOrderTableBinding bind) {
        this.bind = bind;
        bind.baseText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s == null || s.length() <= 0)
                    return;
                double text = Double.parseDouble(s.toString());
                if (text >= minBase && text <= maxBase) {
                    if (isFirst.get()) {
                        if (bind.fiveAccuText.getText() != null || !bind.fiveAccuText.getText().toString().isEmpty()) {
                            double text2 = Double.parseDouble(bind.fiveAccuText.getText().toString());
                            if (text2 >= minFiveBase && text2 <= maxFiveBase)
                                getAllMoneyMessage(String.valueOf(text), String.valueOf(text2));
                        }
                    } else {
                        getMoneyMessage(String.valueOf(text));
                    }

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        bind.fiveAccuText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s == null || s.length() <= 0)
                    return;
                double text = Double.parseDouble(s.toString());
                if (text >= minFiveBase && text <= maxFiveBase) {
                        if( bind.baseText.getText()!=null||!bind.baseText.getText().toString().isEmpty()){
                            double text2 = Double.parseDouble( bind.baseText.getText().toString());
                            if(text2>=minBase&&text2<=maxBase)
                                getAllMoneyMessage(String.valueOf(text2),String.valueOf(text));
                        }

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    public void getMoneyMessage(final String base) {
        new HttpConfigManager().getSocialMoneyConfig(appDur.get(), base, cityName, hourseType, new BeanConfigCallBack<SocialMoneyConfig>() {
            @Override
            public void onDataResponse(SocialMoneyConfig bean) {
                if (bean == null || bean.getData() == null)
                    return;
                defaultBase = base;
                socialSecurity.set(bean.getData().getSocialSecurity());
                personalTax.set(bean.getData().getPersonalTax());
                disabilityInsurance.set(bean.getData().getDisabilityInsurance());
                serviceCharge.set(bean.getData().getServiceCharge());
                overdue_fine.set(bean.getData().getOverdue_fine());
                sum.set(bean.getData().getSum());
            }
        });
    }

    private void getAccuMoneyMessage(final String base) {
        new HttpConfigManager().getAccuMoneyConfig(appDur.get(), base, cityName, new BeanConfigCallBack<SocialMoneyConfig>() {
            @Override
            public void onDataResponse(SocialMoneyConfig bean) {
                if (bean == null || bean.getData() == null)
                    return;
                defaultBase = base;
                accuSecurity.set(bean.getData().getAccumulationFund());
                personalTax.set(bean.getData().getPersonalTax());
                disabilityInsurance.set(bean.getData().getDisabilityInsurance());
                serviceCharge.set(bean.getData().getServiceCharge());
                overdue_fine.set(bean.getData().getOverdue_fine());
                sum.set(bean.getData().getSum());
            }
        });
    }

    private void getAllMoneyMessage(final String base,final String baseAccu) {
        new HttpConfigManager().getAllMoneyConfig(appDur.get(), base, baseAccu,hourseType,cityName, new BeanConfigCallBack<SocialMoneyConfig>() {
            @Override
            public void onDataResponse(SocialMoneyConfig bean) {
                if (bean == null || bean.getData() == null)
                    return;
                defaultBase = base;
                socialSecurity.set(bean.getData().getSocialSecurity());
                accuSecurity.set(bean.getData().getAccumulationFund());
                personalTax.set(bean.getData().getPersonalTax());
                disabilityInsurance.set(bean.getData().getDisabilityInsurance());
                serviceCharge.set(bean.getData().getServiceCharge());
                overdue_fine.set(bean.getData().getOverdue_fine());
                sum.set(bean.getData().getSum());
            }
        });
    }

    public void onClick(View view) {
        DialogUtils.getDialogUtilInstance().showDuringDialog(mContext, new DialogUtils.ItemClickBack() {
            @Override
            public void onBack(String s) {
                appDur.set(s);
                DialogUtils.getDialogUtilInstance().dismiss();
            }
        });
    }

    public void sendOrder() {
        UserDataObtain.getInstance(mContext).getUserFromPhoneRxJava(CommonSettingValue.getIns(mContext).getCurrentPhone(), new IDataObtain.IDBResCallback<DbUser>() {
            @Override
            public void complete(DbUser dbUser) {
                if (isFirst.get()) {

                } else {
                    if (isAccu.get()) {
                        new HttpConfigManager().getSendAccuOrderConfig(
                                dbUser.getUserId(), dbUser.getUserName(), hourseType,
                                tableType.get(), bind.banknameText.getText().toString(),
                                bind.banknumText.getText().toString(), dbUser.getIdCard(),
                                cityName, appDur.get(), defaultBase,
                                socialSecurity.get(), disabilityInsurance.get(), personalTax.get(),
                                serviceCharge.get(), overdue_fine.get(), sum.get(), currentTime, new BeanConfigCallBack<BaseConfig>() {
                                    @Override
                                    public void onDataResponse(BaseConfig bean) {
                                        toastShow(bean.getMsg());
                                    }
                                }
                        );
                    } else {
                        new HttpConfigManager().getSendOrderConfig(
                                dbUser.getUserId(), dbUser.getUserName(), hourseType,
                                tableType.get(), bind.banknameText.getText().toString(),
                                bind.banknumText.getText().toString(), dbUser.getIdCard(),
                                cityName, appDur.get(), defaultBase,
                                accuSecurity.get(), disabilityInsurance.get(), personalTax.get(),
                                serviceCharge.get(), overdue_fine.get(), sum.get(), currentTime, new BeanConfigCallBack<BaseConfig>() {
                                    @Override
                                    public void onDataResponse(BaseConfig bean) {
                                        toastShow(bean.getMsg());
                                    }
                                }
                        );
                    }
                }

            }
        });

    }
}
