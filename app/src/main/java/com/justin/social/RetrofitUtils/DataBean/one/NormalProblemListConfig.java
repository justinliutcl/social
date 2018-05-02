package com.justin.social.RetrofitUtils.DataBean.one;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;

import java.util.List;

/**
 * Created by ASUS on 2018/3/28.
 */

public class NormalProblemListConfig extends BaseConfig {

    private NormalProblemListConfigData data;
    private int totalRecords;
    private int totalPage;
    private int pageIndex;
    private int pageSize;
    private int notify;

    public class NormalProblemListConfigData {
        private List<NormalProblemListConfigData> data;
        private String questionId;
        private String questionTitle;
        private String questionAnswer;
        private String questionUserName;
        private String phone;
        private String status;
        private String createDate;
        private String updateDate;
        private String releaseDate;
        private String order;
        public List<NormalProblemListConfigData> getData() {
            return data;
        }

        public void setData(List<NormalProblemListConfigData> data) {
            this.data = data;
        }

        public String getQuestionId() {
            return questionId;
        }

        public void setQuestionId(String questionId) {
            this.questionId = questionId;
        }

        public String getQuestionTitle() {
            return questionTitle;
        }

        public void setQuestionTitle(String questionTitle) {
            this.questionTitle = questionTitle;
        }

        public String getQuestionAnswer() {
            return "\t\t"+questionAnswer;
        }

        public void setQuestionAnswer(String questionAnswer) {
            this.questionAnswer = questionAnswer;
        }

        public String getQuestionUserName() {
            return questionUserName;
        }

        public void setQuestionUserName(String questionUserName) {
            this.questionUserName = questionUserName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }
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

    public NormalProblemListConfigData getData() {
        return data;
    }

    public void setData(NormalProblemListConfigData data) {
        this.data = data;
    }
}
