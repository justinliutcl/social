package com.justin.social.model.five;

import android.content.Context;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.five.OrderConfig;
import com.justin.social.RetrofitUtils.DataBean.five.OrderConfig2;
import com.justin.social.RetrofitUtils.HttpConfigManager;
import com.justin.social.RetrofitUtils.configRequest.CallBack;
import com.justin.social.accessor.CommonSettingValue;
import com.justin.social.adapter.OrderListContentAdapter;
import com.justin.social.databinding.FragmentOrderlistOneBinding;
import com.justin.social.model.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/4/19.
 */

public class OrderListModel extends BaseModel implements CallBack<OrderConfig2>{
    HttpConfigManager httpConfigManager;
    OrderListContentAdapter adapter;
    FragmentOrderlistOneBinding mBinding;
    String type;

    public OrderListModel(Context context) {
        super(context);
        httpConfigManager = new HttpConfigManager();
    }

    public void initNewsList(String type, OrderConfig orderConfig) {
        this.type = type;
        if (orderConfig != null && orderConfig.getData() != null && !orderConfig.getData().isEmpty()) {
            List<OrderConfig2>list = new ArrayList<>();
            for(int i=0;i<orderConfig.getData().size();i++){
                OrderConfig2 o = new OrderConfig2(orderConfig.getData().get(i).orderNum,
                        orderConfig.getData().get(i).createTime,
                        orderConfig.getData().get(i).insuredTime,
                        orderConfig.getData().get(i).allCharge,
                        orderConfig.getData().get(i).status,
                        orderConfig.getData().get(i).type,
                        orderConfig.getData().get(i).orderStatus,
                this);
                list.add(o);
            }
            adapter = new OrderListContentAdapter(type, list, mContext);
            mBinding.list.setAdapter(adapter);
        }
    }

    public void init(FragmentOrderlistOneBinding mBinding) {
        this.mBinding = mBinding;
    }

    public void deleteOrderNum(){
        new HttpConfigManager().deleteOrderNumConfig(CommonSettingValue.getIns(mContext).getCurrentUserId(), type, new BeanConfigCallBack<BaseConfig>() {
            @Override
            public void onDataResponse(BaseConfig bean) {

            }
        });
    }

    @Override
    public void onCall(OrderConfig2 orderConfig) {
        if(adapter!=null){
            adapter.mDataList.remove(orderConfig);
            adapter.notifyDataSetChanged();
        }
    }
}
