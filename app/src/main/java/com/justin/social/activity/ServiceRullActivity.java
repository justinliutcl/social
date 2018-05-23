package com.justin.social.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.one.PoliceConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.databinding.ActivityPoliceDetialBinding;
import com.justin.social.model.one.OrderTableModel;
import com.justin.social.utils.ImageUtils;

public class ServiceRullActivity extends BackActivity {
    ActivityPoliceDetialBinding bind;
    private OrderTableModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this,R.layout.activity_police_detial);
        initContent();
    }

    BeanConfigCallBack callBack =  new BeanConfigCallBack<PoliceConfig>() {
        @Override
        public void onDataResponse(PoliceConfig bean) {
            bind.newTitle.setText(bean.getData().getTitleOne());
            bind.top.setText(bean.getData().getTitleTwo());
            bind.content.setText(bean.getData().getContent());
            ImageUtils.setImage(bind.image,bean.getData().getTitleImg());

        }
    };

    private void initContent(){
        String titleBar="服务协议";
        new HttpConfigManager().getPoliceDetialConfig(PoliceDetialActivity.SERVICE_RULE_TYPE,callBack);
        bind.title.setText(titleBar);

    }

}
