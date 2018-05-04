package com.justin.social.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.five.OrderConfig;
import com.justin.social.databinding.ItemDuringOrderListBinding;
import com.justin.social.databinding.ItemOrderListBinding;
import com.justin.social.databinding.ItemOrderPaymentListBinding;

import java.util.List;

/**
 * Created by Justinliu on 2017/12/4.
 */

public class ThreeOrderListContentAdapter extends BaseAdapter<BaseHolder<ViewDataBinding>, OrderConfig> {

    private List<OrderConfig> mDataList;
    private Context context;

    public ThreeOrderListContentAdapter( List<OrderConfig> mDataList, Context context) {
        this.mDataList = mDataList;
        this.context = context;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ContentHolder(DataBindingUtil.inflate(LayoutInflater.from(context),
                    R.layout.item_order_payment_list, parent, false), this);
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        holder.bindTo(mDataList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return 1;
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
    public void onbindTo(final BaseHolder<ViewDataBinding> viewDataBindingBaseHolder, final OrderConfig model, int position) {
        if (viewDataBindingBaseHolder.mBinding instanceof ItemOrderPaymentListBinding) {
            ((ItemOrderPaymentListBinding) viewDataBindingBaseHolder.mBinding).setModel(model);
        }

    }

}
