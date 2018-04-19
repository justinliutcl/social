package com.justin.social.model.one;

import android.content.Context;

import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.one.NewListBean;
import com.justin.social.RetrofitUtils.DataBean.one.NewsListConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.adapter.NewsListAdapter;
import com.justin.social.adapter.NewsListContentAdapter;
import com.justin.social.databinding.FragmentNewslistOneBinding;
import com.justin.social.databinding.FragmentOneBinding;
import com.justin.social.model.base.BaseModel;

import java.util.List;

/**
 * Created by ASUS on 2018/4/19.
 */

public class NewsListModel extends BaseModel {
    HttpConfigManager httpConfigManager;
    NewsListContentAdapter adapter;
    FragmentNewslistOneBinding mBinding;
    public NewsListModel(Context context) {
        super(context);
        httpConfigManager = new HttpConfigManager();
    }

    public void initNewsList(String type){
        httpConfigManager.getNewListConfig(type, new BeanConfigCallBack<NewsListConfig>() {
            @Override
            public void onDataResponse(NewsListConfig bean) {
                if(bean!=null){
                    List<NewListBean> list = bean.getData().getData();
                    if (list != null) {
                        for (NewListBean listBean : list) {
                            listBean.context = mContext;
                        }
                        adapter = new NewsListContentAdapter(list, mContext);
                        mBinding.list.setAdapter(adapter);
                    }
                }

            }
        });
    }

    public void init(FragmentNewslistOneBinding mBinding) {
        this.mBinding = mBinding;
    }

}
