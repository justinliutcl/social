package com.justin.social.RetrofitUtils.DataBean.one;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/4/5.
 */

public class CityConfig extends BaseConfig implements Serializable{
    List<CityConfig> data;
    public String cityId;
    public String cityName;
    public String socialSecurityBaseLow;
    public String socialSecurityBaseHigh;
    public String accumulationFundBaseLow;
    public String accumulationFundBaseHigh;

    public List<CityConfig> getData() {
        return data;
    }

    public void setData(List<CityConfig> data) {
        this.data = data;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getSocialSecurityBaseLow() {
        return socialSecurityBaseLow;
    }

    public void setSocialSecurityBaseLow(String socialSecurityBaseLow) {
        this.socialSecurityBaseLow = socialSecurityBaseLow;
    }

    public String getSocialSecurityBaseHigh() {
        return socialSecurityBaseHigh;
    }

    public void setSocialSecurityBaseHigh(String socialSecurityBaseHigh) {
        this.socialSecurityBaseHigh = socialSecurityBaseHigh;
    }

    public String getAccumulationFundBaseLow() {
        return accumulationFundBaseLow;
    }

    public void setAccumulationFundBaseLow(String accumulationFundBaseLow) {
        this.accumulationFundBaseLow = accumulationFundBaseLow;
    }

    public String getAccumulationFundBaseHigh() {
        return accumulationFundBaseHigh;
    }

    public void setAccumulationFundBaseHigh(String accumulationFundBaseHigh) {
        this.accumulationFundBaseHigh = accumulationFundBaseHigh;
    }

    public List<String> getStringListCity(){
        List<String>list = new ArrayList<>();
        if(data!=null){
            for(CityConfig cityConfig:data){
                list.add(cityConfig.getCityName());
            }
        }
        return list;
    }

    public int getIndex(String s){
        List<String>list = new ArrayList<>();
        if(data!=null){
            for(CityConfig cityConfig:data){
                list.add(cityConfig.getCityName());
            }
        }
        return list.indexOf(s);
    }
}
