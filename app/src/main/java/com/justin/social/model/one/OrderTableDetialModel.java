package com.justin.social.model.one;

import android.content.Context;
import android.databinding.ObservableBoolean;

import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.four.SocialTool;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.adapter.SocialToolNormalAdapter;
import com.justin.social.databinding.ActivityOrderDetialTableBinding;
import com.justin.social.model.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/3/31.
 */

public class OrderTableDetialModel extends BaseModel {
    SocialToolNormalAdapter adapter;
    public  String name,idCard,base,starTime,city,typeName,accu;
    public String individual, residual, serviceMoney, overdel;
    public ObservableBoolean isSelect;
    public String num,type,money;


    public OrderTableDetialModel(Context context,String num,String type,String money,
                                 String name,String idCard,String base,String starTime,String city,
                                 String typeName,String individual,String residual,String serviceMoney,
                                 String overdel,String accu) {
        super(context);
        isSelect = new ObservableBoolean(true);
        this.num = num;
        this.type = type;
        this.money = money;
        this.name = name;
        this.idCard = idCard;
        this.base = base;
        this.starTime = starTime;
        this.city = city;
        this.typeName = typeName;
        this.individual = individual;
        this.residual = residual;
        this.serviceMoney = serviceMoney;
        this.overdel = overdel;
        this.accu = accu;
    }


    public void initBind(final ActivityOrderDetialTableBinding bind) {
        new HttpConfigManager().getToolMoneyConfig(base, accu,CommonSettingValue.getIns(mContext).getHourseType(CommonSettingValue.getIns(mContext).getCurrentPhone()),city, new BeanConfigCallBack<SocialTool>() {
            @Override
            public void onDataResponse(SocialTool bean) {
                if (bean == null || bean.getData() == null)
                    return;
                List<SocialTool.SocialItem> list = new ArrayList<>();
                SocialTool.SocialItem item = new SocialTool.SocialItem();
                item.setTitle("类别");
                item.setBase("基数");
                item.setSingle("个人");
                item.setCompany("公司");
                item.setSum("总计");
                list.add(item);
                bean.getData().getEndowmentInsurance().setTitle("养老");
                bean.getData().getMedicalInsurance().setTitle("医疗");
                bean.getData().getUnemploymentInsurance().setTitle("失业");
                bean.getData().getEmploymentInjuryInsurance().setTitle("工伤");
                bean.getData().getMaternityInsurance().setTitle("生育");
                bean.getData().getAccumulationFund().setTitle("公积金");

                bean.getData().getSum().setTitle("总计");
                switch (typeName){
                    case "社保":
                        list.add(bean.getData().getEndowmentInsurance());
                        list.add(bean.getData().getMedicalInsurance());
                        list.add(bean.getData().getUnemploymentInsurance());
                        list.add(bean.getData().getEmploymentInjuryInsurance());
                        list.add(bean.getData().getMaternityInsurance());
                        list.add(bean.getData().getSum());
                        break;
                    case "五险一金":
                        list.add(bean.getData().getEndowmentInsurance());
                        list.add(bean.getData().getMedicalInsurance());
                        list.add(bean.getData().getUnemploymentInsurance());
                        list.add(bean.getData().getEmploymentInjuryInsurance());
                        list.add(bean.getData().getMaternityInsurance());
                        list.add(bean.getData().getAccumulationFund());
                        list.add(bean.getData().getSum());
                        break;
                    case "公积金":
                        list.add(bean.getData().getAccumulationFund());
                        list.add(bean.getData().getSum());
                        break;
                }

                adapter = new SocialToolNormalAdapter(list, mContext, null);
                bind.list.setAdapter(adapter);
            }
        });
    }
}
