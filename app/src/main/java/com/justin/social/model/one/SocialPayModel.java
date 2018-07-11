package com.justin.social.model.one;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.justin.social.MainActivity;
import com.justin.social.R;
import com.justin.social.RXDbUtils.DB.UserDataObtain;
import com.justin.social.RXDbUtils.DBbean.DbUser;
import com.justin.social.RXDbUtils.DBbean.IDataObtain;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.five.YouhuijuanConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.activity.OrderTableDetailActivity;
import com.justin.social.activity.SocialPayActivity;
import com.justin.social.adapter.DialogYouhuiJuanAdapter;
import com.justin.social.alipay2.AliPayUse;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.AppUtils;
import com.justin.social.utils.ContentKey;
import com.justin.social.utils.DialogUtils;
import com.justin.social.wxapi.WePayUser;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;

/**
 * Created by ASUS on 2018/3/31.
 */

public class SocialPayModel extends BaseModel {
    public  String name,idCard,base,accu,starTime,city,typeName;
    private String individual, residual, serviceMoney, overdel;
    public ObservableBoolean isSelect;
    public String num,type;
    public ObservableField<String>youhuijuan;
    public ObservableField<String>money;
    YouhuijuanConfig mYouhuijuan;


    public SocialPayModel(Context context,String num,String type,String money,
                          String name,String idCard,String base,String starTime,String city,
                          String typeName,String individual,String residual,String serviceMoney,
                          String overdel,String accu) {
        super(context);
        isSelect = new ObservableBoolean(true);
        youhuijuan = new ObservableField<String>("请选择优惠券");
        this.num = num;
        this.type = type;
        this.money = new ObservableField<>(money);
        this.name = name;
        this.idCard = idCard;
        this.base = base;
        this.starTime = starTime;
        this.city = city;
        this.typeName = typeName;
        this.individual = individual;
        this.residual = residual;
        this.serviceMoney = serviceMoney;
        this.overdel = overdel;
        this.accu = accu;

    }

    public void onNextClick(View view){
//        mContext.startActivity(new Intent(mContext, SocialPayActivity.class));
//        ((Activity)mContext).finish();
    }

    public void onClick(View view){
        if(isSelect.get()){
            aliPay();
        }else {
            weiPay();
        }


    }

    public void onYouhuiJuanClick(final View view) {
        view.setClickable(false);
        UserDataObtain.getInstance(mContext).getCurrentUser(new IDataObtain.IDBResCallback<DbUser>() {
            @Override
            public void complete(DbUser dbUser) {
                view.setClickable(true);
                new HttpConfigManager().getYouhuiJuanConfig(dbUser.getUserId(), new BeanConfigCallBack<YouhuijuanConfig>() {
                    @Override
                    public void onDataResponse(YouhuijuanConfig bean) {
                        if(bean == null || bean.getData().isEmpty()){
                            Toast.makeText(mContext,"暂无优惠券",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        DialogUtils.getDialogUtilInstance().showYouhuiJuanDialog(mContext, new DialogUtils.ItemYouhuijuanClickBack() {
                            @Override
                            public void onBack(YouhuijuanConfig s) {
//                Toast.makeText(mContext,s,Toast.LENGTH_SHORT).show();
                                youhuijuan.set("已选择：" + s.getCouponValue() + "元");
                                mYouhuijuan = s;
                                double m = Double.parseDouble(money.get()) - Double.parseDouble(s.getCouponValue());
                                money.set(m+"");
                                DialogUtils.getDialogUtilInstance().dismiss();
                            }
                        });
                    }
                });
            }
        });

    }

    public void onOrderDetialClick(View view){
        OrderTableDetailActivity.JumpToOrder(mContext,num,type,money.get(),name,idCard,base,starTime,city,typeName,
                individual, residual, serviceMoney, overdel,accu);
    }
    public void onAlipyClick(View view) {
        isSelect.set(true);
    }

    public void onWechatClick(View view) {
        isSelect.set(false);
    }

    public void aliPay() {
        String yId = mYouhuijuan == null ? "0" : mYouhuijuan.couponId;
        AliPayUse pay = new AliPayUse(mContext, typeName, Double.parseDouble(money.get()), type, num, ContentKey.ALIPAY_URL, yId, new AliPayUse.OnPayCall() {
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

    public void weiPay() {
        String yId = mYouhuijuan == null ? "0" : mYouhuijuan.couponId;
        WePayUser.wePay(mContext, num, type, Double.parseDouble(money.get()), yId);
    }

    @BindingAdapter("selectType")
    public static void setSelect(ImageView view, boolean isSelect){
        Glide.with(view.getContext().getApplicationContext()).load(isSelect?R.drawable.icon_pay_select:R.drawable.icon_pay_normal)
                .into(view);
    }
}
