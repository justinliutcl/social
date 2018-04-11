package com.justin.social.RetrofitUtils.DataBean.one;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.TextView;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.activity.NewsActivity;
import com.justin.social.utils.AppUtils;

/**
 * Created by ASUS on 2018/3/31.
 */

public class NewListBean extends BaseConfig{
    public String contentId;
    public String contentTypeId;
    public String title;
    public String sort;
    public String titleImg;
    public String contentImg;
    public String link;
    public long createDate;
    public long updateDate;
    public long releaseDate;
    public String state;
    public String status;
    public String txt;
    public Context context;
    private NewListBean data;

    public NewListBean getData() {
        return data;
    }

    public void setData(NewListBean data) {
        this.data = data;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getContentTypeId() {
        return contentTypeId;
    }

    public void setContentTypeId(String contentTypeId) {
        this.contentTypeId = contentTypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public String getContentImg() {
        return contentImg;
    }

    public void setContentImg(String contentImg) {
        this.contentImg = contentImg;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }

    public long getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(long releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    @BindingAdapter("time")
    public static void setTime(TextView view, long time){
       view.setText( AppUtils.getNewstime(time));
    }

    public void onItemClick(View view){
        NewsActivity.JumpToNews(context,contentId);
    }
}
