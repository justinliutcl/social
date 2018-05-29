package com.justin.social.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.one.PoliceConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.databinding.ActivityOrderTableBinding;
import com.justin.social.databinding.ActivityPoliceDetialBinding;
import com.justin.social.model.one.OrderTableModel;
import com.justin.social.utils.ImageUtils;

public class PoliceDetialActivity extends BackActivity {
    public static final int HOURSE_TYPE             = 1;
    public static final int CAR_TYPE                = 2;
    public static final int SCHOOL_TYPE             = 3;
    public static final int LOCIAL_TYPE             = 4;
    public static final int PHYSICAL_LOCAL_TYPE     = 5;
    public static final int GROUP_TYPE              = 6;
    public static final int SERVICE_RULE_TYPE       = 7;
    public static final int PHYSICAL_INTER_TYPE       = 8;
    public static final String      TYPE    = "detial_type";
    ActivityPoliceDetialBinding bind;
    private OrderTableModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this,R.layout.activity_police_detial);
        initContent(getIntent().getIntExtra(TYPE,HOURSE_TYPE));
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

    private void initContent(int type){
        String titleBar="";
        switch (type){
            case HOURSE_TYPE:
                new HttpConfigManager().getPoliceDetialConfig(HOURSE_TYPE,callBack);
                titleBar =  getString(R.string.police_hourse_title_bar);
                break;
            case CAR_TYPE:
                titleBar =  getString(R.string.police_car_title_bar);
                new HttpConfigManager().getPoliceDetialConfig(CAR_TYPE,callBack);
                break;
            case SCHOOL_TYPE:
                titleBar =  getString(R.string.police_school_title_bar);
                new HttpConfigManager().getPoliceDetialConfig(SCHOOL_TYPE,callBack);
                break;
            case LOCIAL_TYPE:
                titleBar =  getString(R.string.police_loacl_title_bar);
                new HttpConfigManager().getPoliceDetialConfig(LOCIAL_TYPE,callBack);
                break;
        }
        bind.title.setText(titleBar);

    }

    public static void jumpToPoliceDetial(Context context,int type){
        Intent intent = new Intent(context,PoliceDetialActivity.class);
        intent.putExtra(TYPE,type);
        context.startActivity(intent);
    }
}
