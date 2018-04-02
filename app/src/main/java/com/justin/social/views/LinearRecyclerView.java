package com.justin.social.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

/**
 * Created by BlueSky on 2017/12/5.
 */

public class LinearRecyclerView extends RecyclerView {
    public LinearRecyclerView(Context context) {
        super(context);
        initView();
    }

    public LinearRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LinearRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new SafeLinearLayoutManager(getContext());
        setLayoutManager(layoutManager);
        setItemAnimator(new DefaultItemAnimator());
        setNestedScrollingEnabled(false);
    }

    public class SafeLinearLayoutManager extends LinearLayoutManager {
        public SafeLinearLayoutManager(Context context) {
            super(context);
        }

        @Override
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            try {
                super.onLayoutChildren(recycler, state);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
            AdvertiseLinearSmoothScroller linearSmoothScroller =
                    new AdvertiseLinearSmoothScroller(recyclerView.getContext());
            linearSmoothScroller.setTargetPosition(position);
            startSmoothScroll(linearSmoothScroller);
        }

    }

    class AdvertiseLinearSmoothScroller extends LinearSmoothScroller {

        public AdvertiseLinearSmoothScroller(Context context) {
            super(context);
        }

        /**
         * @param viewStart      RecyclerView的top位置
         * @param viewEnd        RecyclerView的Bottom位置
         * @param boxStart       item的top位置
         * @param boxEnd         item的bottom位置
         * @param snapPreference 滑动方向的识别
         * @return
         */
        @Override
        public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
            return boxStart - viewStart;//返回的就是我们item置顶需要的偏移量
        }

        /**
         * 此方法返回滚动每1px需要的时间,可以用来控制滚动速度
         * 即如果返回2ms，则每滚动1000px，需要2秒钟
         *
         * @param displayMetrics
         * @return
         */
        @Override
        protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
            return super.calculateSpeedPerPixel(displayMetrics);
        }


    }

    public abstract static class RecyclerViewScrollDetector extends RecyclerView.OnScrollListener {
        private int mScrollThreshold;

        public abstract void onScrollUp(RecyclerView recyclerView, int dx, int dy);

        public abstract void onScrollDown(RecyclerView recyclerView, int dx, int dy);

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            boolean isSignificantDelta = Math.abs(dy) > mScrollThreshold;
            if (isSignificantDelta) {
                if (dy > 0) {
                    onScrollUp(recyclerView, dx, dy);
                } else {
                    onScrollDown(recyclerView, dx, dy);
                }
            }
        }

        public void setScrollThreshold(int scrollThreshold) {
            mScrollThreshold = scrollThreshold;
        }
    }

}
