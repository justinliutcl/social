package com.justin.social.model.one;

import android.content.Context;

import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.one.NewListBean;
import com.justin.social.RetrofitUtils.DataBean.one.NewsListConfig;
import com.justin.social.RetrofitUtils.DataBean.one.NormalProblemListConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.adapter.NewsListContentAdapter;
import com.justin.social.adapter.NormalProblemListContentAdapter;
import com.justin.social.databinding.ActivityNormalProblemBinding;
import com.justin.social.databinding.FragmentNewslistOneBinding;
import com.justin.social.model.base.BaseModel;

import java.util.List;

/**
 * Created by ASUS on 2018/4/19.
 */

public class NormalProblemModel extends BaseModel {
    HttpConfigManager httpConfigManager;
    NormalProblemListContentAdapter adapter;
    ActivityNormalProblemBinding  mBinding;
    public NormalProblemModel(Context context) {
        super(context);
        httpConfigManager = new HttpConfigManager();
    }

    public void initNewsList(){
        httpConfigManager.getNormalProblemConfig( new BeanConfigCallBack<NormalProblemListConfig>() {
            @Override
            public void onDataResponse(NormalProblemListConfig bean) {
                if(bean!=null){
                    List<NormalProblemListConfig.NormalProblemListConfigData> list = bean.getData().getData();
                    if (list != null) {
                        adapter = new NormalProblemListContentAdapter(list, mContext);
                        mBinding.list.setAdapter(adapter);
                    }
                }

            }
        });
    }

    public void initBind(ActivityNormalProblemBinding binding) {
        mBinding = binding;
    }
}
