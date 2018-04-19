package com.justin.social.RetrofitUtils.DataBean.one;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;

import java.util.List;

/**
 * Created by ASUS on 2018/3/28.
 */

public class NewsListConfig extends BaseConfig {
    public static final String SOCIAL_HOT   = "1";
    public static final String SOCIAL_NEWS  = "2";
    public static final String POLICE_NEWS  = "3";
    public static final String MAIN_NEWS    = "4";

    private NewsListConfigData data;
    private int totalRecords;
    private int totalPage;
    private int pageIndex;
    private int pageSize;
    private int notify;

    public class NewsListConfigData{
        private List<NewListBean>data;

        public List<NewListBean> getData() {
            return data;
        }

        public void setData(List<NewListBean> data) {
            this.data = data;
        }
    }
    public NewsListConfigData getData() {
        return data;
    }


    public void setData(NewsListConfigData data) {
        this.data = data;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getNotify() {
        return notify;
    }

    public void setNotify(int notify) {
        this.notify = notify;
    }
}
