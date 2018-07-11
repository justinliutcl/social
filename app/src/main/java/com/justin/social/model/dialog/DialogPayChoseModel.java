package com.justin.social.model.dialog;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;

import com.justin.social.MainActivity;
import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.one.AboutMeConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.alipay2.AliPayUse;
import com.justin.social.databinding.DialogDuringBinding;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.AppUtils;
import com.justin.social.utils.ContentKey;
import com.justin.social.utils.DialogUtils;
import com.justin.social.wxapi.WePayUser;

/**
 * Created by ASUS on 2018/4/6.
 */

public class DialogPayChoseModel extends BaseModel {

    private  String typeName;
    private  String type;
    private  String orderNum;
    private  double money;
    public ObservableBoolean isSelect;

    public DialogPayChoseModel(Context context,String typeName,String type,String orderNum,double money) {
        super(context);
        isSelect = new ObservableBoolean(true);
        this.typeName = typeName;
        this.type = type;
        this.orderNum = orderNum;
        this.money = money;
    }

    public void onCancleClick(View view) {
        if(isSelect.get()){
            aliPay();
        }else {
            weiPay();
        }
        DialogUtils.getDialogUtilInstance().dismiss();
    }

    public void aliPay(){
        AliPayUse pay = new AliPayUse( mContext, typeName, money, type,orderNum, ContentKey.ALIPAY_URL, new AliPayUse.OnPayCall() {
            @Override
            public void SuccessCallBack(String mes) {

                toastShow("支付成功");
                MainActivity.JumpTMain(mContext);
            }

            @Override
            public void failCallBack(String mes) {
                toastShow("支付失败");
            }
        });
        pay.pay();
    }

    public void weiPay(){
        WePayUser.wePay(mContext,orderNum,type,money,"0");
    }


    public void onClick(View view) {
    }

    public void onAlipyClick(View view){
        isSelect.set(true);
    }

    public void onWechatClick(View view){
        isSelect.set(false);
    }
}
