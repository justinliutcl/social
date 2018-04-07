package com.justin.social.RetrofitUtils.DataBean.one;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;

/**
 * Created by ASUS on 2018/4/6.
 */

public class SocialMoneyConfig extends BaseConfig {
    private SocialMoneyConfig data;
    private String personalTax;
    private String serviceCharge;
    private String sum;
    private String disabilityInsurance;
    private String socialSecurity;
    private String overdue_fine;

    public SocialMoneyConfig getData() {
        return data;
    }

    public void setData(SocialMoneyConfig data) {
        this.data = data;
    }

    public String getPersonalTax() {
        return personalTax;
    }

    public void setPersonalTax(String personalTax) {
        this.personalTax = personalTax;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getDisabilityInsurance() {
        return disabilityInsurance;
    }

    public void setDisabilityInsurance(String disabilityInsurance) {
        this.disabilityInsurance = disabilityInsurance;
    }

    public String getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(String socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public String getOverdue_fine() {
        return overdue_fine;
    }

    public void setOverdue_fine(String overdue_fine) {
        this.overdue_fine = overdue_fine;
    }
}
