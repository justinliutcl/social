package com.justin.social.RetrofitUtils.DataBean.one;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/4/5.
 */

public class SendImageConfig extends BaseConfig implements Serializable{
    public SendImageConfig data;
    public String idCardImgTrueSide;
    public String idCardImgFalseSide;
    public String hokouFirst;
    public String hukouMy;
    public String imgUser;

    public SendImageConfig getData() {
        return data;
    }

    public void setData(SendImageConfig data) {
        this.data = data;
    }

    public String getIdCardImgTrueSide() {
        return idCardImgTrueSide;
    }

    public void setIdCardImgTrueSide(String idCardImgTrueSide) {
        this.idCardImgTrueSide = idCardImgTrueSide;
    }

    public String getIdCardImgFalseSide() {
        return idCardImgFalseSide;
    }

    public void setIdCardImgFalseSide(String idCardImgFalseSide) {
        this.idCardImgFalseSide = idCardImgFalseSide;
    }

    public String getHokouFirst() {
        return hokouFirst;
    }

    public void setHokouFirst(String hokouFirst) {
        this.hokouFirst = hokouFirst;
    }

    public String getHukouMy() {
        return hukouMy;
    }

    public void setHukouMy(String hukouMy) {
        this.hukouMy = hukouMy;
    }

    public String getImgUser() {
        return imgUser;
    }

    public void setImgUser(String imgUser) {
        this.imgUser = imgUser;
    }
}
