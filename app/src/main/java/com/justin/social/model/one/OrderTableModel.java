package com.justin.social.model.one;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.activity.OrderTableActivity;
import com.justin.social.activity.SocialPayActivity;
import com.justin.social.databinding.ActivityOrderTableBinding;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.AppUtils;
import com.justin.social.utils.DialogUtils;

/**
 * Created by ASUS on 2018/3/31.
 */

public class OrderTableModel extends BaseModel {
    public ObservableBoolean isAccu;
    public ObservableField<String> baseType;
    public ObservableField<String> section;
    private ActivityOrderTableBinding bind;
    private double minBase;
    private double maxBase;
    private String cityName;
    private String hourseType;
    public String currentTime;
    public String defaultBase;
    public String tableType;

    public OrderTableModel(Context context) {
        super(context);
        isAccu = new ObservableBoolean(false);
        baseType = new ObservableField<>("");
        section = new ObservableField<>("");
        currentTime = AppUtils.getTime("yyyy-MM-dd");
    }

    public void onNextClick(View view){
        mContext.startActivity(new Intent(mContext, SocialPayActivity.class));
    }

    public void initType(boolean isAcc,String min,String max){
        isAccu.set(isAcc);
        baseType.set(isAcc?"公积金基数":"社保基数");
        this.section.set(min+" - "+max);
        minBase = Double.parseDouble(min);
        maxBase = Double.parseDouble(max);
        CommonSettingValue value = CommonSettingValue.getIns(mContext);
        if(isAcc){
            String base = value.getAccuBase(value.getCurrentPhone());
            defaultBase = base == null?String.valueOf(minBase):base;
            tableType = 
        }else{
            String base = value.getSocialBase(value.getCurrentPhone());
            defaultBase = base == null?String.valueOf(minBase):base;
        }
    }

    public void initBind(ActivityOrderTableBinding bind) {
        this.bind = bind;
        bind.baseText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s == null||s.length()<=0)
                    return;
                double text = Double.parseDouble(s.toString());
                if (text >= minBase && text <= maxBase) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void getdetial(){

    }

    public void onClick(View view){
        DialogUtils.getDialogUtilInstance().showDuringDialog(mContext, new DialogUtils.ItemClickBack() {
            @Override
            public void onBack(String s) {
                toastShow(s);
            }
        });
    }

}
