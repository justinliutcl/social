//package com.justin.social.adapter;
//
//import android.content.Context;
//import android.databinding.DataBindingUtil;
//import android.databinding.ViewDataBinding;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.hawk.music.R;
//import com.hawk.music.databinding.PlaylistContentItemBinding;
//import com.hawk.music.databinding.PlaylistTitleItemBinding;
//import com.hawk.music.viewmodels.library.libraryBean.PlayListBean;
//
//import java.util.List;
//
///**
// * Created by Justinliu on 2017/12/4.
// */
//
//public class PlayListAdapter extends BaseAdapter<BaseHolder<ViewDataBinding>, PlayListBean> {
//
//    private List<PlayListBean> mDataList;
//    private Context context;
//
//    public PlayListAdapter(List<PlayListBean> mDataList, Context context) {
//        this.mDataList = mDataList;
//        this.context = context;
//    }
//
//    @Override
//    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (viewType == PlayListBean.PLAYLIST_AUTO_CONTENT_TYPE || viewType == PlayListBean.PLAYLIST__CUSTOM_CONTENT_TYPE) {
//            return new ContentHolder(DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.playlist_content_item, parent, false),this);
//        } else {
//            return new ContentHolder(DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.playlist_title_item, parent, false),this);
//        }
//    }
//
//    @Override
//    public void onBindViewHolder(BaseHolder holder, int position) {
//        holder.bindTo(mDataList.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        return mDataList.size();
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return mDataList.get(position).getmType();
//    }
//
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
//
//    @Override
//    public void onbindTo(final BaseHolder<ViewDataBinding> viewDataBindingBaseHolder, final PlayListBean model) {
//        if(viewDataBindingBaseHolder.mBinding instanceof PlaylistContentItemBinding){
//            ((PlaylistContentItemBinding)viewDataBindingBaseHolder.mBinding).setModel(model);
//            ((PlaylistContentItemBinding)viewDataBindingBaseHolder.mBinding).tvListImage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    model.onItemClick(((PlaylistContentItemBinding)viewDataBindingBaseHolder.mBinding).playListContainer);
//                }
//            });
//        }else if(viewDataBindingBaseHolder.mBinding instanceof PlaylistTitleItemBinding){
//            ((PlaylistTitleItemBinding)viewDataBindingBaseHolder.mBinding).setModel(model);
//        }
//    }
//
//}
