package com.justin.social.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.one.NewListBean;
import com.justin.social.databinding.ItemNewListBinding;
import com.justin.social.databinding.ItemNewsContentListBinding;

import java.util.List;

/**
 * Created by Justinliu on 2017/12/4.
 */

public class NewsListContentAdapter extends BaseAdapter<BaseHolder<ViewDataBinding>, NewListBean> {

    private List<NewListBean> mDataList;
    private Context context;

    public NewsListContentAdapter(List<NewListBean> mDataList, Context context) {
        this.mDataList = mDataList;
        this.context = context;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContentHolder(DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.item_news_content_list, parent, false), this);
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
    public void onbindTo(final BaseHolder<ViewDataBinding> viewDataBindingBaseHolder, final NewListBean model, int position) {
        if (viewDataBindingBaseHolder.mBinding instanceof ItemNewsContentListBinding) {
            ((ItemNewsContentListBinding) viewDataBindingBaseHolder.mBinding).setModel(model);
        }

    }

}
