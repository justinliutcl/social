package com.justin.social.RetrofitUtils.DataBean.three;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.alipay2.AliPayUse;
import com.justin.social.utils.ContentKey;
import com.justin.social.utils.DialogUtils;

/**
 * Created by ASUS on 2018/5/21.
 */

public class ThreeConfig extends BaseConfig {
    public ThreeConfig data;
    public ThreeConfig accumulation;
    public ThreeConfig socialSecurity;
    public String id;
    public String userId;
    public String userName;
    public String orderNum;
    public String idCard;
    public String insuredCity;
    public String insuredTime;
    public String insuredType;
    public String bankName;
    public String branchNum;
    public String applyDuration;
    public String insuredCardinal;
    public String accumulationCharge;
    public String singleCharge;
    public String disabilityCharge;
    public String overdueFine;
    public String serviceCharge;
    public String allCharge;
    public String createTime;
    public String applyTime;
    public String orderStatus;
    public String status;
    public String statusStr;
    public String type = "1";

    public ThreeConfig getData() {
        return data;
    }

    public void setData(ThreeConfig data) {
        this.data = data;
    }

    public ThreeConfig getAccumulation() {
        return accumulation;
    }

    public void setAccumulation(ThreeConfig accumulation) {
        this.accumulation = accumulation;
    }

    public ThreeConfig getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(ThreeConfig socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getInsuredCity() {
        return insuredCity;
    }

    public void setInsuredCity(String insuredCity) {
        this.insuredCity = insuredCity;
    }

    public String getInsuredTime() {
        return insuredTime;
    }

    public void setInsuredTime(String insuredTime) {
        this.insuredTime = insuredTime;
    }

    public String getInsuredType() {
        return insuredType;
    }

    public void setInsuredType(String insuredType) {
        this.insuredType = insuredType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(String branchNum) {
        this.branchNum = branchNum;
    }

    public String getApplyDuration() {
        return applyDuration;
    }

    public void setApplyDuration(String applyDuration) {
        this.applyDuration = applyDuration;
    }

    public String getInsuredCardinal() {
        return insuredCardinal;
    }

    public void setInsuredCardinal(String insuredCardinal) {
        this.insuredCardinal = insuredCardinal;
    }

    public String getAccumulationCharge() {
        return accumulationCharge;
    }

    public void setAccumulationCharge(String accumulationCharge) {
        this.accumulationCharge = accumulationCharge;
    }

    public String getSingleCharge() {
        return singleCharge;
    }

    public void setSingleCharge(String singleCharge) {
        this.singleCharge = singleCharge;
    }

    public String getDisabilityCharge() {
        return disabilityCharge;
    }

    public void setDisabilityCharge(String disabilityCharge) {
        this.disabilityCharge = disabilityCharge;
    }

    public String getOverdueFine() {
        return overdueFine;
    }

    public void setOverdueFine(String overdueFine) {
        this.overdueFine = overdueFine;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getAllCharge() {
        return allCharge;
    }

    public void setAllCharge(String allCharge) {
        this.allCharge = allCharge;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public void onClick(final View view){
        String title= "社保订单";
        String t="0";
        switch (type){
            case "1":
                title= "社保订单";
                t = ContentKey.ORDER_TYPE_SOCIAL;
                break;
            case "2":
                title= "公积金订单";
                t = ContentKey.ORDER_TYPE_ACCU;
                break;
        }
        DialogUtils.getDialogUtilInstance().showPayChoseDialog(view.getContext(),title,t,orderNum,0.01);
//        AliPayUse pay = new AliPayUse( view.getContext(), title, 0.01, t,orderNum, ContentKey.ALIPAY_URL, new AliPayUse.OnPayCall() {
//            @Override
//            public void SuccessCallBack(String mes) {
//                Toast.makeText(view.getContext(),"支付成功",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void failCallBack(String mes) {
//                Toast.makeText(view.getContext(),"支付失败",Toast.LENGTH_SHORT).show();
//            }
//        });
//        pay.pay();
    }

    @BindingAdapter("titleType")
    public static void setTitleType(TextView text, String type){
        String title= "社保订单";
        switch (type){
            case "1":
                title= "社保订单";
                break;
            case "2":
                title= "公积金订单";
                break;
        }
        text.setText(title);
    }
}
