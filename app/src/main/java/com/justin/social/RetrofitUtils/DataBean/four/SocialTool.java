package com.justin.social.RetrofitUtils.DataBean.four;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;

/**
 * Created by ASUS on 2018/4/15.
 */

public class SocialTool extends BaseConfig {
    private SocialTool data;
    private SocialItem endowmentInsurance;
    private SocialItem accumulationFund;
    private SocialItem medicalInsurance;
    private SocialItem unemploymentInsurance;
    private SocialItem employmentInjuryInsurance;
    private SocialItem maternityInsurance;
    private SocialItem sum;

    private String accumulation;
    private String sumAll;
    private String socialSecurity;

    public static class SocialItem{
        private String base;
        private String single;
        private String company;
        private String sum;
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBase() {
            return base;
        }

        public void setBase(String base) {
            this.base = base;
        }

        public String getSingle() {
            return single;
        }

        public void setSingle(String single) {
            this.single = single;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }
    }

    public SocialTool getData() {
        return data;
    }

    public void setData(SocialTool data) {
        this.data = data;
    }

    public SocialItem getEndowmentInsurance() {
        return endowmentInsurance;
    }

    public void setEndowmentInsurance(SocialItem endowmentInsurance) {
        this.endowmentInsurance = endowmentInsurance;
    }

    public SocialItem getAccumulationFund() {
        return accumulationFund;
    }

    public void setAccumulationFund(SocialItem accumulationFund) {
        this.accumulationFund = accumulationFund;
    }

    public SocialItem getMedicalInsurance() {
        return medicalInsurance;
    }

    public void setMedicalInsurance(SocialItem medicalInsurance) {
        this.medicalInsurance = medicalInsurance;
    }

    public SocialItem getUnemploymentInsurance() {
        return unemploymentInsurance;
    }

    public void setUnemploymentInsurance(SocialItem unemploymentInsurance) {
        this.unemploymentInsurance = unemploymentInsurance;
    }

    public SocialItem getEmploymentInjuryInsurance() {
        return employmentInjuryInsurance;
    }

    public void setEmploymentInjuryInsurance(SocialItem employmentInjuryInsurance) {
        this.employmentInjuryInsurance = employmentInjuryInsurance;
    }

    public SocialItem getMaternityInsurance() {
        return maternityInsurance;
    }

    public void setMaternityInsurance(SocialItem maternityInsurance) {
        this.maternityInsurance = maternityInsurance;
    }

    public SocialItem getSum() {
        return sum;
    }

    public void setSum(SocialItem sum) {
        this.sum = sum;
    }

    public String getAccumulation() {
        return accumulation;
    }

    public void setAccumulation(String accumulation) {
        this.accumulation = accumulation;
    }

    public String getSumAll() {
        return sumAll;
    }

    public void setSumAll(String sumAll) {
        this.sumAll = sumAll;
    }

    public String getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(String socialSecurity) {
        this.socialSecurity = socialSecurity;
    }
}
