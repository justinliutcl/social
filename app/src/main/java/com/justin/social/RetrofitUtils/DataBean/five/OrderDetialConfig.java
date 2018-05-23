package com.justin.social.RetrofitUtils.DataBean.five;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;

import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2018/5/19.
 */

public class OrderDetialConfig extends BaseConfig {
    public List<OrderDetialConfig> data;
    public String userId;
    public String userName;
    public String orderNum;
    public String householdType;
    public String insuredType;
    public String branchNum;
    public String idCard;
    public String insuredCity;
    public String insuredTime;
    public String applyDuration;
    public String insuredCardinal;
    public String insuredCharge;
    public String disabilityCharge;
    public String singleCharge;
    public String serviceCharge;
    public String overdueFine;
    public String allCharge;
    public String createTime;
    public String applyTime;
    public String orderStatus;
    public String status;
    public String statusStr;

    public List<OrderDetialConfig> getData() {
        return data;
    }

    public void setData(List<OrderDetialConfig> data) {
        this.data = data;
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

    public String getHouseholdType() {
        return householdType;
    }

    public void setHouseholdType(String householdType) {
        this.householdType = householdType;
    }

    public String getInsuredType() {
        return insuredType;
    }

    public void setInsuredType(String insuredType) {
        this.insuredType = insuredType;
    }

    public String getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(String branchNum) {
        this.branchNum = branchNum;
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

    public String getInsuredCharge() {
        return insuredCharge;
    }

    public void setInsuredCharge(String insuredCharge) {
        this.insuredCharge = insuredCharge;
    }

    public String getDisabilityCharge() {
        return disabilityCharge;
    }

    public void setDisabilityCharge(String disabilityCharge) {
        this.disabilityCharge = disabilityCharge;
    }

    public String getSingleCharge() {
        return singleCharge;
    }

    public void setSingleCharge(String singleCharge) {
        this.singleCharge = singleCharge;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getOverdueFine() {
        return overdueFine;
    }

    public void setOverdueFine(String overdueFine) {
        this.overdueFine = overdueFine;
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

    public OrderDetialConfig getOrderData(){
        if(data!=null&&!data.isEmpty()){
            return data.get(0);
        }
        return null;
    }
}
