package com.justin.social.RetrofitUtils.DataBean.one;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/4/5.
 */

public class HosptialConfig extends BaseConfig implements Serializable{
    public HosptialConfig data;
    public String userId;
    public String hospitalOne;
    public String hospitalTwo;
    public String hospitalThree;
    public String hospitalFour;
    public String hospitalFive;

    public HosptialConfig getData() {
        return data;
    }

    public void setData(HosptialConfig data) {
        this.data = data;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHospitalOne() {
        return hospitalOne;
    }

    public void setHospitalOne(String hospitalOne) {
        this.hospitalOne = hospitalOne;
    }

    public String getHospitalTwo() {
        return hospitalTwo;
    }

    public void setHospitalTwo(String hospitalTwo) {
        this.hospitalTwo = hospitalTwo;
    }

    public String getHospitalThree() {
        return hospitalThree;
    }

    public void setHospitalThree(String hospitalThree) {
        this.hospitalThree = hospitalThree;
    }

    public String getHospitalFour() {
        return hospitalFour;
    }

    public void setHospitalFour(String hospitalFour) {
        this.hospitalFour = hospitalFour;
    }

    public String getHospitalFive() {
        return hospitalFive;
    }

    public void setHospitalFive(String hospitalFive) {
        this.hospitalFive = hospitalFive;
    }
}
