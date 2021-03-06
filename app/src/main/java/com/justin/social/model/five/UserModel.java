package com.justin.social.model.five;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;

import com.justin.social.R;
import com.justin.social.RXDbUtils.DB.UserDataObtain;
import com.justin.social.RXDbUtils.DBbean.DbUser;
import com.justin.social.RXDbUtils.DBbean.IDataObtain;
import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.five.OrderConfig;
import com.justin.social.RetrofitUtils.DataBean.five.UserInfoConfig;
import com.justin.social.RetrofitUtils.DataBean.one.CityConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.adapter.OrderListContentAdapter;
import com.justin.social.databinding.ActivityUserMessageBinding;
import com.justin.social.databinding.ActivityWriteSocialNoteBinding;
import com.justin.social.databinding.FragmentOrderlistOneBinding;
import com.justin.social.model.base.BaseModel;
import com.justin.social.utils.DialogUtils;

/**
 * Created by ASUS on 2018/4/19.
 */

public class UserModel extends BaseModel {
    HttpConfigManager httpConfigManager;
    ActivityUserMessageBinding mBinding;
    public ObservableField<String> city;
    public ObservableField<String> hourseType;
    public ObservableField<String> phone;
    public ObservableField<String> branchName;
    public ObservableField<String> branchNum;
    public ObservableField<String> email;
    CityConfig cityConfig;
    DbUser mUser;

    public UserModel(Context context) {
        super(context);
        httpConfigManager = new HttpConfigManager();
        phone = new ObservableField<>(CommonSettingValue.getIns(mContext).getCurrentPhone());
        city = new ObservableField<>("北京");
        hourseType = new ObservableField<>("本市城镇职工");
        branchName = new ObservableField<>("");
        branchNum = new ObservableField<>("");
        email = new ObservableField<>("");
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                if(mUser==null)
                    return;
                httpConfigManager.getSendUserMesConfig(mBinding.numText.getText().toString(),
                        mBinding.idCardText.getText().toString(),
                        mUser.getUserId(),
                        city.get(),
                        hourseType.get(),
                        mBinding.phoneText.getText().toString(),
                        mBinding.emailText.getText().toString(),
                        mBinding.banknameText.getText().toString(),
                        mBinding.banknumText.getText().toString(), new BeanConfigCallBack<BaseConfig>() {
                            @Override
                            public void onDataResponse(BaseConfig bean) {
                                toastShow(bean.getMsg());
                                mUser.setInsuredCity(city.get());
                                mUser.setIdCard(mBinding.idCardText.getText().toString());
                                mUser.setHouseholdType(hourseType.get());
                                mUser.setEmail(mBinding.emailText.getText().toString());
                                mUser.setBranchName( mBinding.banknameText.getText().toString());
                                mUser.setBranchNum( mBinding.banknumText.getText().toString());
                                UserDataObtain.getInstance(mContext).updataUser(mUser,null);
                            }
                        }
                );
                break;
        }
    }

    public void setData(final ActivityUserMessageBinding bind) {
        this.mBinding = bind;
        UserDataObtain.getInstance(mContext).getUserFromPhoneRxJava(CommonSettingValue.getIns(mContext).getCurrentPhone(), new IDataObtain.IDBResCallback<DbUser>() {
            @Override
            public void complete(DbUser dbUser) {
                mUser = dbUser;
                if (dbUser.userName != null) {
                    bind.numText.setText(dbUser.userName);
                }
                if (dbUser.householdType != null) {
                    hourseType.set(dbUser.householdType);
                }
                if (dbUser.insuredCity != null) {
                    city.set(dbUser.insuredCity);
                }
                if (dbUser.idCard != null) {
                    bind.idCardText.setText(dbUser.idCard);
                }
                if (dbUser.branchName != null) {
                    branchName.set(dbUser.branchName);
                }
                if (dbUser.branchNum != null) {
                    branchNum.set(dbUser.branchNum);
                }
                if (dbUser.email != null) {
                    email.set(dbUser.email);
                }
            }
        });

        new HttpConfigManager().getUserInfoConfig(CommonSettingValue.getIns(mContext).getCurrentUserId(), new BeanConfigCallBack<UserInfoConfig>() {
            @Override
            public void onDataResponse(UserInfoConfig bean) {

                if (bean.getData().getUserName() != null) {
                    bind.numText.setText(bean.getData().getUserName());
                }
                if (bean.getData().householdType != null) {
                    hourseType.set(bean.getData().householdType);
                }
                if (bean.getData().insuredCity != null) {
                    city.set(bean.getData().insuredCity);
                }
                if (bean.getData().idCard != null) {
                    bind.idCardText.setText(bean.getData().idCard);
                }
                if (bean.getData().branchName != null) {
                    branchName.set(bean.getData().branchName);
                }
                if (bean.getData().branchNum != null) {
                    branchNum.set(bean.getData().branchNum);
                }
                if (bean.getData().email != null) {
                    email.set(bean.getData().email);
                }
            }
        });

    }

    private DialogUtils.ItemClickBack cityCall = new DialogUtils.ItemClickBack() {
        @Override
        public void onBack(String s) {
            DialogUtils.getDialogUtilInstance().dismiss();
            if (!city.get().equals(s)) {
                int index = cityConfig.getIndex(s);
                city.set(s);
            }
        }
    };

    public void onShowDialogClick(View view) {
        switch (view.getId()) {
            case R.id.city_ll:
                if (cityConfig != null) {
                    DialogUtils.getDialogUtilInstance().showCityDialog(mContext, cityConfig.getStringListCity(), cityCall);
                }
                break;
            case R.id.regist_type_ll:
                DialogUtils.getDialogUtilInstance().showHourseTypeDialog(mContext, new DialogUtils.ItemClickBack() {
                    @Override
                    public void onBack(String s) {
                        hourseType.set(s);
                        DialogUtils.getDialogUtilInstance().dismiss();
                    }
                });
                break;
        }
    }
}
