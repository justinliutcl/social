package com.justin.social.model.one;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;

import com.justin.social.R;
import com.justin.social.RXDbUtils.DB.UserDataObtain;
import com.justin.social.RXDbUtils.DBbean.DbUser;
import com.justin.social.RXDbUtils.DBbean.IDataObtain;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.one.CityConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.activity.OrderTableActivity;
import com.justin.social.databinding.ActivityInsertServiceBinding;
import com.justin.social.databinding.ActivityWriteSocialNoteBinding;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.DialogUtils;

/**
 * Created by ASUS on 2018/3/31.
 */

public class InsertServiceModel extends BaseModel {

    private ActivityInsertServiceBinding bind;
    HttpConfigManager manager;
    public int type;
    public ObservableField<String> serviceType;
    public ObservableField<String> sum;
    public DbUser user;
    public InsertServiceModel(Context context) {
        super(context);
        manager = new HttpConfigManager();
        CommonSettingValue value = CommonSettingValue.getIns(mContext);
        serviceType = new ObservableField<>("请选择服务类型");
        sum = new ObservableField<>("0.00");
    }



    public void onNextClick(View view) {

    }

    public void setData(final ActivityInsertServiceBinding bind) {
        this.bind = bind;
        bind.list.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
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
                DialogUtils.getDialogUtilInstance().showHourseTypeDialog(mContext, new DialogUtils.ItemClickBack() {
                    @Override
                    public void onBack(String s) {
                        serviceType.set(s);
                        DialogUtils.getDialogUtilInstance().dismiss();
                    }
                });
                break;
        }
    }
}
