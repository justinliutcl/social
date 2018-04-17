package com.justin.social.model.one;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;

import com.justin.social.R;
import com.justin.social.RXDbUtils.DB.UserDataObtain;
import com.justin.social.RXDbUtils.DBbean.DbUser;
import com.justin.social.RXDbUtils.DBbean.IDataObtain;
import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.one.CityConfig;
import com.justin.social.RetrofitUtils.DataBean.one.ServiceAddConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.activity.OrderTableActivity;
import com.justin.social.adapter.InsertServiceAdapter;
import com.justin.social.databinding.ActivityInsertServiceBinding;
import com.justin.social.databinding.ActivityWriteSocialNoteBinding;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.AccountUtils;
import com.justin.social.utils.AppUtils;
import com.justin.social.utils.DialogUtils;

import java.util.List;

/**
 * Created by ASUS on 2018/3/31.
 */

public class InsertServiceModel extends BaseModel {

    private ActivityInsertServiceBinding bind;
    HttpConfigManager manager;
    InsertServiceAdapter adapter;
    public int type;
    public ObservableField<String> serviceType;
    public ObservableField<String> sum;
    private String change;
    public DbUser user;
    ServiceAddConfig config;
    List<ServiceAddConfig> mList;

    public InsertServiceModel(Context context) {
        super(context);
        manager = new HttpConfigManager();
        CommonSettingValue value = CommonSettingValue.getIns(mContext);
        serviceType = new ObservableField<>("请选择服务类型");
        sum = new ObservableField<>("0.00");
    }


    public void onNextClick(View view) {

        if (bind.name.getText().toString().isEmpty()) {
            toastShow("请输入姓名");
            return;
        }
        if (!AccountUtils.isIDCard(bind.idcard.getText().toString())) {
            toastShow("请输入正确身份证号");
            return;
        }
        if (mList == null || mList.isEmpty()) {
            toastShow("请选择正确增值服务");
            return;
        }

        manager.sendServiceAddConfig(bind.name.getText().toString(), bind.idcard.getText().toString(), change, sum.get(), new BeanConfigCallBack<BaseConfig>() {
            @Override
            public void onDataResponse(BaseConfig bean) {
                if(bean.isSuccess()){
                    toastShow("提交成功");
                }
            }
        });
    }

    public void setData(final ActivityInsertServiceBinding bind) {
        this.bind = bind;
        bind.list.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        UserDataObtain.getInstance(mContext).getUserFromPhoneRxJava(CommonSettingValue.getIns(mContext).getCurrentPhone(),
                new IDataObtain.IDBResCallback<DbUser>() {
                    @Override
                    public void complete(DbUser dbUser) {
                        user = dbUser;
                        if (dbUser.userName != null) {
                            bind.name.setText(dbUser.userName);
                        }
                        if (dbUser.idCard != null) {
                            bind.idcard.setText(dbUser.idCard);
                        }

                    }
                });
    }

    private DialogUtils.ItemClickBack cityCall = new DialogUtils.ItemClickBack() {
        @Override
        public void onBack(String s) {
            DialogUtils.getDialogUtilInstance().dismiss();
        }
    };

    public void onShowDialogClick(View view) {
        switch (view.getId()) {
            case R.id.service_type_ll:
                if (config != null) {
                    DialogUtils.getDialogUtilInstance().showServiceAddDialog(mContext, config, new DialogUtils.ItemObjectClickBack<List<ServiceAddConfig>>() {
                        @Override
                        public void onBack(List<ServiceAddConfig> s) {
//                            serviceType.set(s);
                            mList = s;
                            change="";
                            double sums=0;
                            for (ServiceAddConfig config : mList) {
                                change+=config.getServiceName();
                                sums+= Double.parseDouble(config.getServiceCharge());
                            }
                            sum.set(AppUtils.get2Double(sums));
                            DialogUtils.getDialogUtilInstance().dismiss();
                            adapter = new InsertServiceAdapter(s, mContext);
                            bind.list.setAdapter(adapter);
                        }
                    });
                }

                break;
        }
    }

    public void initService() {
        config = CommonSettingValue.getIns(mContext).getServiceAdd();
        manager.getServiceAddConfig(new BeanConfigCallBack<ServiceAddConfig>() {
            @Override
            public void onDataResponse(ServiceAddConfig bean) {
                if (bean != null) {
                    config = bean;
                    CommonSettingValue.getIns(mContext).setServiceAdd(bean);
                }
            }
        });
    }
}