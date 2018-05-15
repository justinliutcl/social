package com.justin.social.RetrofitUtils.DataBean.one;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;

/**
 * Created by ASUS on 2018/5/14.
 */

public class PoliceConfig extends BaseConfig {
    private PoliceConfig data;
    private String titleOne;
    private String titleTwo;
    private String titleImg;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PoliceConfig getData() {
        return data;
    }

    public void setData(PoliceConfig data) {
        this.data = data;
    }

    public String getTitleOne() {
        return titleOne;
    }

    public void setTitleOne(String titleOne) {
        this.titleOne = titleOne;
    }

    public String getTitleTwo() {
        return titleTwo;
    }

    public void setTitleTwo(String titleTwo) {
        this.titleTwo = titleTwo;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }
}
