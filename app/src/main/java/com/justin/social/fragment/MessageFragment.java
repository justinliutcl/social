package com.justin.social.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.justin.social.R;
import com.justin.social.RXDbUtils.DB.UserDataObtain;
import com.justin.social.RXDbUtils.DBbean.DbUser;
import com.justin.social.RXDbUtils.DBbean.IDataObtain;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.five.HeaderImageConfig;
import com.justin.social.RetrofitUtils.DataBean.one.NewsListConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.databinding.FragmentMessageBinding;
import com.justin.social.databinding.FragmentNewslistOneBinding;
import com.justin.social.model.one.NewsListModel;
import com.justin.social.model.tab.FiveModel;
import com.justin.social.utils.ImageUtils;
import com.justin.social.utils.PhotoSelectUtil;
import com.justin.social.utils.PhotoSelectUtilA;

/**
 * Created by Justinliu on 2018/3/27.
 */

public class MessageFragment extends Fragment implements View.OnClickListener{
    private FragmentMessageBinding mBinding;
    public static final int TYPE_IDCARD_POST     = 2;
    public static final int TYPE_IDCARD_NAGA     = 3;
    public static final int TYPE_HOME_POST       = 4;
    public static final int TYPE_HOME_NAGA       = 5;
    public static final int TYPE_USER            = 1;
    private int type;
    PhotoSelectUtil photoUtil;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_message, container, false);
        initView();
        return mBinding.getRoot();
    }

    private void initView() {
        mBinding.idcardPost.setOnClickListener(this);
        mBinding.idcardNaga.setOnClickListener(this);
        mBinding.homePost.setOnClickListener(this);
        mBinding.homeNaga.setOnClickListener(this);
        mBinding.userPhoto.setOnClickListener(this);
        photoUtil = new PhotoSelectUtil(getActivity(), this, new PhotoSelectUtilA.onGetCallBack() {
            @Override
            public void onBase64Callback(String base64) {
                new HttpConfigManager().getMessageImageConfig(CommonSettingValue.getIns(getActivity()).getCurrentUserId(), base64,String.valueOf(type),
                        new BeanConfigCallBack<HeaderImageConfig>() {
                            @Override
                            public void onDataResponse(HeaderImageConfig bean) {
                                if(bean == null)
                                    return;
                                if (bean.isSuccess()) {
                                    switch (type){
                                        case TYPE_IDCARD_POST:
                                            ImageUtils.setIcon(mBinding.idcardPost, bean.getData());
                                            break;
                                        case TYPE_IDCARD_NAGA:
                                            ImageUtils.setIcon(mBinding.idcardNaga, bean.getData());
                                            break;
                                        case TYPE_HOME_POST:
                                            ImageUtils.setIcon(mBinding.homePost, bean.getData());
                                            break;
                                        case TYPE_HOME_NAGA:
                                            ImageUtils.setIcon(mBinding.homeNaga, bean.getData());
                                            break;
                                        case TYPE_USER:
                                            ImageUtils.setIcon(mBinding.userPhoto, bean.getData());
                                            break;
                                    }

                                    Toast.makeText(getActivity(), "上传成功", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.idcard_post:
                onImageClick(mBinding.idcardPost);
                type = TYPE_IDCARD_POST;
                break;
            case R.id.idcard_naga:
                onImageClick(mBinding.idcardNaga);
                type = TYPE_IDCARD_NAGA;
                break;
            case R.id.home_post:
                onImageClick(mBinding.homePost);
                type = TYPE_HOME_POST;
                break;
            case R.id.home_naga:
                onImageClick(mBinding.homeNaga);
                type = TYPE_HOME_NAGA;
                break;
            case R.id.user_photo:
                onImageClick(mBinding.userPhoto);
                type = TYPE_USER;
                break;
        }
    }
    public void onImageClick(ImageView view) {
        photoUtil.setimg(view);
        photoUtil.showDialog();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        photoUtil.forresult(requestCode, resultCode, data);
    }
}