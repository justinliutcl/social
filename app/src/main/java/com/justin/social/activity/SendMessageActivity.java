package com.justin.social.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.one.NewsListConfig;
import com.justin.social.databinding.ActivityNewslistBinding;
import com.justin.social.databinding.ActivitySendmessageBinding;
import com.justin.social.fragment.MessageFragment;
import com.justin.social.fragment.MessageHospFragment;
import com.justin.social.fragment.NewsFragmentOne;

import java.util.ArrayList;

public class SendMessageActivity extends BackActivity {
    ActivitySendmessageBinding bind;
    public static final String TYPE = "send_type";
    public static final int TYPE_ONE = 0;
    public static final int TYPE_TWO = 1;
    private FragmentManager mFm;
    private ArrayList<Fragment> mFragmentList = new ArrayList<Fragment>();
    private String[] mFragmentTagList = {"newsOne", "newsTwo"};
    private Fragment mCurrentFragmen = null;
    TextView textView;
    TextView textView1;
    TextView line;
    TextView line2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_sendmessage);
        initData();
        initView();
    }

    private void initView() {
        textView = (TextView) findViewById(R.id.hot_tv);
        textView1 = (TextView) findViewById(R.id.social_tv);
        line = (TextView) findViewById(R.id.hot_line);
        line2 = (TextView) findViewById(R.id.social_line);

        if (getIntent().getIntExtra(TYPE, 0) != 0) {
            onSocialClick(null);
        }
    }

    private void initData() {

        MessageFragment oneFragment = new MessageFragment();
        MessageHospFragment twoFragment = new MessageHospFragment();
        mFragmentList.add(0, oneFragment);
        mFragmentList.add(1, twoFragment);
        mCurrentFragmen = mFragmentList.get(0);
        // 初始化首次进入时的Fragment
        mFm = getFragmentManager();
        FragmentTransaction transaction = mFm.beginTransaction();
        transaction.add(R.id.content, mCurrentFragmen, mFragmentTagList[0]);
        transaction.commitAllowingStateLoss();

    }

    public void onHotClick(View view) {
        textView.setTextColor(getResources().getColor(R.color.text_green));
        line.setBackgroundColor(getResources().getColor(R.color.text_green));

        textView1.setTextColor(getResources().getColor(R.color.text_a7));
        line2.setBackgroundColor(getResources().getColor(R.color.white));
        switchFragment(mFragmentList.get(0), mFragmentTagList[0]);
    }

    public void onSocialClick(View view) {
        textView.setTextColor(getResources().getColor(R.color.text_a7));
        line.setBackgroundColor(getResources().getColor(R.color.white));

        textView1.setTextColor(getResources().getColor(R.color.text_green));
        line2.setBackgroundColor(getResources().getColor(R.color.text_green));
        switchFragment(mFragmentList.get(1), mFragmentTagList[1]);
    }

    // 转换Fragment
    void switchFragment(Fragment to, String tag) {
        if (mCurrentFragmen != to) {
            FragmentTransaction transaction = mFm.beginTransaction();
            if (!to.isAdded()) {
                // 没有添加过:     // 隐藏当前的，添加新的，显示新的
                transaction.hide(mCurrentFragmen).add(R.id.content, to, tag).show(to);
            } else {
                // 隐藏当前的，显示新的
                transaction.hide(mCurrentFragmen).show(to);
            }
            mCurrentFragmen = to;
            transaction.commitAllowingStateLoss();
        }
    }

    public static void JumpSendMessage(Context context, int type) {
        Intent intent = new Intent(context, SendMessageActivity.class);
        intent.putExtra(TYPE, type);
        context.startActivity(intent);
    }
}
