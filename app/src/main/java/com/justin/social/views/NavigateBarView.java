package com.justin.social.views;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.justin.social.R;
import com.justin.social.databinding.ViewNavigateBarBinding;


/**
 * Created by BlueSky on 2017/11/29.
 */

public class NavigateBarView extends RelativeLayout {

    private NavigateBarChangeListener listener;
    ViewNavigateBarBinding mBind;

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
        mBind = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.view_navigate_bar, this, true);
        attachListener();
    }

    public void onMusicVideo() {

    }

    public void onOne() {
        mBind.oneIv.setImageResource(R.drawable.icon_tab_home_select);
        mBind.twoIv.setImageResource(R.drawable.icon_tab_servicenormal);
        mBind.threeIv.setImageResource(R.drawable.icon_tab_pay);
        mBind.fourIv.setImageResource(R.drawable.icon_tab_tool_normal);
        mBind.fifthIv.setImageResource(R.drawable.icon_tab_user_normal);

        mBind.oneTv.    setTextColor(getResources().getColor(R.color.base_green3));
        mBind.twoTv.    setTextColor(getResources().getColor(R.color.base_black4));
        mBind.threeTv.  setTextColor(getResources().getColor(R.color.base_black4));
        mBind.fourTv.   setTextColor(getResources().getColor(R.color.base_black4));
        mBind.fifthTv.  setTextColor(getResources().getColor(R.color.base_black4));
        if(listener!=null)
            listener.onTabChaged(0);
    }
    public void onTwo() {
        mBind.oneIv.setImageResource(R.drawable.icon_tab_home_normal);
        mBind.twoIv.setImageResource(R.drawable.icon_tab_service_select);
        mBind.threeIv.setImageResource(R.drawable.icon_tab_pay);
        mBind.fourIv.setImageResource(R.drawable.icon_tab_tool_normal);
        mBind.fifthIv.setImageResource(R.drawable.icon_tab_user_normal);

        mBind.oneTv.  setTextColor(getResources().getColor(R.color.base_black4));
        mBind.twoTv.  setTextColor(getResources().getColor(R.color.base_green3));
        mBind.threeTv.setTextColor(getResources().getColor(R.color.base_black4));
        mBind.fourTv. setTextColor(getResources().getColor(R.color.base_black4));
        mBind.fifthTv.setTextColor(getResources().getColor(R.color.base_black4));
        if(listener!=null)
            listener.onTabChaged(1);
    }
    public void onThree() {
        mBind.oneIv.setImageResource(R.drawable.icon_tab_home_normal);
        mBind.twoIv.setImageResource(R.drawable.icon_tab_servicenormal);
        mBind.threeIv.setImageResource(R.drawable.icon_tab_pay);
        mBind.fourIv.setImageResource(R.drawable.icon_tab_tool_normal);
        mBind.fifthIv.setImageResource(R.drawable.icon_tab_user_normal);

        mBind.oneTv.  setTextColor(getResources().getColor(R.color.base_black4));
        mBind.twoTv.  setTextColor(getResources().getColor(R.color.base_black4));
        mBind.threeTv.setTextColor(getResources().getColor(R.color.base_green3));
        mBind.fourTv. setTextColor(getResources().getColor(R.color.base_black4));
        mBind.fifthTv.setTextColor(getResources().getColor(R.color.base_black4));
        if(listener!=null)
            listener.onTabChaged(2);
    }
    public void onFour() {
        mBind.oneIv.setImageResource(R.drawable.icon_tab_home_normal);
        mBind.twoIv.setImageResource(R.drawable.icon_tab_servicenormal);
        mBind.threeIv.setImageResource(R.drawable.icon_tab_pay);
        mBind.fourIv.setImageResource(R.drawable.icon_tab_tool_select);
        mBind.fifthIv.setImageResource(R.drawable.icon_tab_user_normal);

        mBind.oneTv.  setTextColor(getResources().getColor(R.color.base_black4));
        mBind.twoTv.  setTextColor(getResources().getColor(R.color.base_black4));
        mBind.threeTv.setTextColor(getResources().getColor(R.color.base_black4));
        mBind.fourTv. setTextColor(getResources().getColor(R.color.base_green3));
        mBind.fifthTv.setTextColor(getResources().getColor(R.color.base_black4));
        if(listener!=null)
            listener.onTabChaged(3);
    }
    public void onFifth() {
        mBind.oneIv.setImageResource(R.drawable.icon_tab_home_normal);
        mBind.twoIv.setImageResource(R.drawable.icon_tab_servicenormal);
        mBind.threeIv.setImageResource(R.drawable.icon_tab_pay);
        mBind.fourIv.setImageResource(R.drawable.icon_tab_tool_normal);
        mBind.fifthIv.setImageResource(R.drawable.icon_tab_user_select);

        mBind.oneTv.  setTextColor(getResources().getColor(R.color.base_black4));
        mBind.twoTv.  setTextColor(getResources().getColor(R.color.base_black4));
        mBind.threeTv.setTextColor(getResources().getColor(R.color.base_black4));
        mBind.fourTv. setTextColor(getResources().getColor(R.color.base_black4));
        mBind.fifthTv.setTextColor(getResources().getColor(R.color.base_green3));
        if(listener!=null)
            listener.onTabChaged(4);
    }


    private void attachListener() {
        mBind.tabOne.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onOne();
            }
        });
        mBind.tabTwo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onTwo();
            }
        });
        mBind.tabThree.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onThree();
            }
        });
        mBind.tabFour.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onFour();
            }
        });
        mBind.tabFive.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onFifth();
            }
        });
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void setNavigateBarChangeListener(NavigateBarChangeListener listener) {
        this.listener = listener;
    }

    public interface NavigateBarChangeListener {
        void onTabChaged(int tabPostion);
    }
}
