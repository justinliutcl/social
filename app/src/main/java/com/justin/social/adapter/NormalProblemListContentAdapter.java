package com.justin.social.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.one.NewListBean;
import com.justin.social.RetrofitUtils.DataBean.one.NormalProblemListConfig;
import com.justin.social.databinding.ItemNewsContentListBinding;
import com.justin.social.databinding.ItemProblemContentListBinding;

import java.util.List;

/**
 * Created by Justinliu on 2017/12/4.
 */

public class NormalProblemListContentAdapter extends BaseAdapter<BaseHolder<ViewDataBinding>, NormalProblemListConfig.NormalProblemListConfigData> {

    private List<NormalProblemListConfig.NormalProblemListConfigData> mDataList;
    private Context context;

    public NormalProblemListContentAdapter(List<NormalProblemListConfig.NormalProblemListConfigData> mDataList, Context context) {
        this.mDataList = mDataList;
        this.context = context;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContentHolder(DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.item_problem_content_list, parent, false), this);
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
    public void onbindTo(final BaseHolder<ViewDataBinding> viewDataBindingBaseHolder, final NormalProblemListConfig.NormalProblemListConfigData model, int position) {
        if (viewDataBindingBaseHolder.mBinding instanceof ItemProblemContentListBinding) {
            ((ItemProblemContentListBinding) viewDataBindingBaseHolder.mBinding).setModel(model);
        }

    }

}
