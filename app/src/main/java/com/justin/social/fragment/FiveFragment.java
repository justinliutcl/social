package com.justin.social.fragment;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.justin.social.R;
import com.justin.social.RXDbUtils.DB.UserDataObtain;
import com.justin.social.RXDbUtils.DBbean.DbUser;
import com.justin.social.RXDbUtils.DBbean.IDataObtain;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.five.HeaderImageConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.activity.FindFriendActivity;
import com.justin.social.activity.OrderFlowActivity;
import com.justin.social.activity.ResetCodeActivity;
import com.justin.social.activity.ShareFriendActivity;
import com.justin.social.activity.UserMessageActivity;
import com.justin.social.databinding.FragmentFiveBinding;
import com.justin.social.model.tab.FiveModel;
import com.justin.social.utils.ImageUtils;
import com.justin.social.utils.PermessionUtils;
import com.justin.social.utils.PhotoSelectUtil;
import com.justin.social.utils.PhotoSelectUtilA;

/**
 * Created by Justinliu on 2018/3/27.
 */

public class FiveFragment extends Fragment implements View.OnClickListener {
    private FragmentFiveBinding mBinding;
    PhotoSelectUtil photoUtil;
    FiveModel model;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_five, container, false);
        initView();
        return mBinding.getRoot();
    }

    private void initView() {
        photoUtil = new PhotoSelectUtil(getActivity(), this, new PhotoSelectUtilA.onGetCallBack() {
            @Override
            public void onBase64Callback(String base64) {
                new HttpConfigManager().getHeadImageConfig(CommonSettingValue.getIns(getActivity()).getCurrentUserId(), base64,
                        new BeanConfigCallBack<HeaderImageConfig>() {
                            @Override
                            public void onDataResponse(HeaderImageConfig bean) {
                                if (bean.isSuccess()) {
                                    UserDataObtain.getInstance(getActivity()).updataCurrentHeadImage(bean.getData(), null);
                                    ImageUtils.setIcon(mBinding.titleIv, bean.getData());
                                    Toast.makeText(getActivity(), "上传成功", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        mBinding.titleIv.setOnClickListener(this);
        mBinding.friendLl.setOnClickListener(this);
        mBinding.orderFlowLl.setOnClickListener(this);
        mBinding.orderFlowLl.setOnClickListener(this);
        mBinding.userMessage.setOnClickListener(this);
        mBinding.resetCodeLl.setOnClickListener(this);
        model = new FiveModel(getActivity());
        UserDataObtain.getInstance(getActivity()).getCurrentUser(new IDataObtain.IDBResCallback<DbUser>() {
            @Override
            public void complete(DbUser user) {
                model.headImage.set(user.headImg);
                model.name.set(user.userName);
            }
        });

        mBinding.setModel(model);
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        photoUtil.forresult(requestCode, resultCode, data);
    }

    public void onImageClick(View view) {
        photoUtil.setimg(mBinding.titleIv);
        photoUtil.showDialog();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_iv:
                onImageClick(v);
                break;
            case R.id.order_flow_ll:
                startActivity(new Intent(getActivity(), OrderFlowActivity.class));
                break;
            case R.id.user_message:
                startActivity(new Intent(getActivity(), UserMessageActivity.class));
                break;
            case R.id.reset_code_ll:
                startActivity(new Intent(getActivity(), ResetCodeActivity.class));
                break;
            case R.id.friend_ll:
//                if (ContextCompat.checkSelfPermission(getActivity(),android.Manifest.permission.READ_CONTACTS)
//                        !=PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(getActivity(),
//                            new String[]{android.Manifest.permission.READ_CONTACTS},
//                            1);
//                }else{
//                    startActivity(new Intent(getActivity(), FindFriendActivity.class));
//                }
                startActivity(new Intent(getActivity(), ShareFriendActivity.class));
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(new Intent(getActivity(), FindFriendActivity.class));
                } else {

                }
                return;
            }
        }
    }

}
