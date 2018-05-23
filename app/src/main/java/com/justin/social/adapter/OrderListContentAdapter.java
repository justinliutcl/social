package com.justin.social.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.five.OrderConfig;
import com.justin.social.RetrofitUtils.DataBean.five.OrderConfig2;
import com.justin.social.RetrofitUtils.DataBean.one.NewListBean;
import com.justin.social.databinding.ItemDuringOrderListBinding;
import com.justin.social.databinding.ItemNewsContentListBinding;
import com.justin.social.databinding.ItemOrderListBinding;

import java.util.List;

/**
 * Created by Justinliu on 2017/12/4.
 */

public class OrderListContentAdapter extends BaseAdapter<BaseHolder<ViewDataBinding>, OrderConfig2> {

    public List<OrderConfig2> mDataList;
    private Context context;
    private String type;


    public OrderListContentAdapter(String type,List<OrderConfig2> mDataList, Context context) {
        this.mDataList = mDataList;
        this.context = context;
        this.type = type;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(type.equals(OrderConfig.DURPAY)){
            return new ContentHolder(DataBindingUtil.inflate(LayoutInflater.from(context),
                    R.layout.item_during_order_list, parent, false), this);
        }else{
            return new ContentHolder(DataBindingUtil.inflate(LayoutInflater.from(context),
                    R.layout.item_order_list, parent, false), this);
        }

    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        holder.bindTo(mDataList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
//        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
//        if (manager instanceof GridLayoutManager) {
//            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
//            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                @Override
//                public int getSpanSize(int position) {
//                    if (getItemViewType(position) == PlayListBean.PLAYLIST_AUTO_CONTENT_TYPE || getItemViewType(position) == PlayListBean.PLAYLIST__CUSTOM_CONTENT_TYPE)
//                        return 1;
//                    else
//                        return 2;
//                }
//            });
//        }
//    }

    @Override
    public void onbindTo(final BaseHolder<ViewDataBinding> viewDataBindingBaseHolder, final OrderConfig2 model, int position) {
        if (viewDataBindingBaseHolder.mBinding instanceof ItemOrderListBinding) {
            ((ItemOrderListBinding) viewDataBindingBaseHolder.mBinding).setModel(model);
        }else{
            ((ItemDuringOrderListBinding) viewDataBindingBaseHolder.mBinding).setModel(model);
        }

    }

}
