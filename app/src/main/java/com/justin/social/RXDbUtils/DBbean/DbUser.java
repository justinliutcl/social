package com.justin.social.RXDbUtils.DBbean;

/**
 * Created by Justinliu on 2018/3/21.
 */

public class DbUser {
    public int id;
    public String userId;
    public String userName;
    public String phone;
    public String nikeName;
    public String headImg;
    public String email;
    public String idCard;
    public String insuredCity;
    public String householdType;
    public String bankName;
    public String branchName;
    public String branchNum;
    public String token;
    public String passWord;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getHouseholdType() {
        return householdType;
    }

    public void setHouseholdType(String householdType) {
        this.householdType = householdType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(String branchNum) {
        this.branchNum = branchNum;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public DbUser() {
    }

    public DbUser(int id, String userId, String userName, String phone, String nikeName, String headImg, String email, String idCard, String insuredCity, String householdType, String bankName, String branchName, String branchNum, String token, String passWord) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.phone = phone;
        this.nikeName = nikeName;
        this.headImg = headImg;
        this.email = email;
        this.idCard = idCard;
        this.insuredCity = insuredCity;
        this.householdType = householdType;
        this.bankName = bankName;
        this.branchName = branchName;
        this.branchNum = branchNum;
        this.token = token;
        this.passWord = passWord;
    }
}
