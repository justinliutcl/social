package com.justin.social.views;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.justin.social.R;


/**
 * Created by BlueSky on 2017/11/29.
 */

public class NavigateBarView extends RelativeLayout {

    private static final int PROPORTION = 240;

    private RelativeLayout.LayoutParams layoutParams;
    private NavigateBarChangeListener listener;

    public NavigateBarView(Context context) {
        super(context);
        initView();
    }

    public NavigateBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public NavigateBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public NavigateBarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        ViewNavigateBarBing bing =
        DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.view_navigate_bar, this, true);
        attachListener();
    }

    public void onMusicVideo() {

    }

    public void onLibrary() {
        mBind.libraryIcon.setImageResource(R.drawable.icon_tab_library_select);
        mBind.searchIcon.setImageResource(R.drawable.icon_tab_browse_normal);
        mBind.libraryText.setTextColor(getResources().getColor(R.color.base_red));
        mBind.searchText.setTextColor(getResources().getColor(R.color.base_gray));
        mBind.onlineIcon.setImageResource(R.drawable.icon_tab_hone_normal);
        mBind.onlineText.setTextColor(getResources().getColor(R.color.base_gray));
        mViewModel.onLibraryTabClick();
    }

    private void attachListener() {
        mBind.online.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                mBind.searchIcon.setImageResource(R.drawable.icon_tab_browse_normal);
                mBind.libraryIcon.setImageResource(R.drawable.icon_tab_library_normal);
                mBind.searchText.setTextColor(getResources().getColor(R.color.base_gray));
                mBind.libraryText.setTextColor(getResources().getColor(R.color.base_gray));
                mBind.onlineIcon.setImageResource(R.drawable.icon_tab_hone_select);
                mBind.onlineText.setTextColor(getResources().getColor(R.color.base_red));
                mViewModel.onHomeTabClick();
            }
        });

        mBind.search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mBind.searchIcon.setImageResource(R.drawable.icon_tab_browse_select);
                mBind.libraryIcon.setImageResource(R.drawable.icon_tab_library_normal);
                mBind.searchText.setTextColor(getResources().getColor(R.color.base_red));
                mBind.libraryText.setTextColor(getResources().getColor(R.color.base_gray));
                mBind.onlineIcon.setImageResource(R.drawable.icon_tab_hone_normal);
                mBind.onlineText.setTextColor(getResources().getColor(R.color.base_gray));
                mViewModel.onSearchTabClick();
            }
        });

        mBind.library.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onLibrary();
            }
        });
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
    }

    public void setOnNavigateBarChangeListener(NavigateBarViewModel.NavigateBarChangeListener listener) {
        mViewModel.setNavigateBarChangeListener(listener);
    }

    public void scrollTo(float slideOffset) {
        float scrollY = slideOffset * PROPORTION;
        layoutParams.bottomMargin = -(int) scrollY;
        setLayoutParams(layoutParams);
        setAlpha(1 - slideOffset);
    }

    public void resetState(int currentIndex) {
        switch (currentIndex) {
            case SlidingUpActivity.FIRST:
                mBind.online.performClick();
                break;
            case SlidingUpActivity.SECOND:
                mBind.search.performClick();
                break;
            case SlidingUpActivity.THIRD:
                mBind.library.performClick();
                break;
        }
        mViewModel.resetState(currentIndex);
    }

    public void setNavigateBarChangeListener(NavigateBarChangeListener listener) {
        this.listener = listener;
    }

    public interface NavigateBarChangeListener {
        void onTabChaged(int tabPostion, int preTabPostion);
    }
}
