package com.justin.social;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.justin.social.activity.BaseActivity;
import com.justin.social.fragment.FiveFragment;
import com.justin.social.fragment.FourFragment;
import com.justin.social.fragment.OneFragment;
import com.justin.social.fragment.ThreeFragment;
import com.justin.social.fragment.TwoFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private FragmentManager mFm;
    private ArrayList<Fragment> mFragmentList = new ArrayList<Fragment>();
    private String[] mFragmentTagList = {"OneFragment", "TwoFragment", "ThreeFragment", "fourFragment", "fiveFragment"};
    private Fragment mCurrentFragmen = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {


    }

    private void initData() {
        OneFragment oneFragment = new OneFragment();
        TwoFragment twoFragment = new TwoFragment();
        ThreeFragment threeFragment = new ThreeFragment();
        FourFragment fourFragment = new FourFragment();
        FiveFragment fiveFragment = new FiveFragment();
        mFragmentList.add(0, oneFragment);
        mFragmentList.add(1, twoFragment);
        mFragmentList.add(2, threeFragment);
        mFragmentList.add(3, fourFragment);
        mFragmentList.add(4, fiveFragment);
        mCurrentFragmen = mFragmentList.get(0);
        // 初始化首次进入时的Fragment
        mFm = getFragmentManager();
        FragmentTransaction transaction = mFm.beginTransaction();
        transaction.add(R.id.content, mCurrentFragmen, mFragmentTagList[0]);
        transaction.commitAllowingStateLoss();
    }

    // 当activity非正常销毁时被调用
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        // 重置Fragment，防止当内存不足时导致Fragment重叠
        updateFragment(outState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_one:
                switchFragment(mFragmentList.get(0), mFragmentTagList[0]);
                break;
//            case R.id.ib_two:
//                switchFragment(mFragmentList.get(1), mFragmentTagList[1]);
//                break;
//            case R.id.ib_three:
//                switchFragment(mFragmentList.get(2), mFragmentTagList[2]);
//                break;
        }
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

    private void updateFragment(Bundle outState) {
        mFm = getFragmentManager();
        if (outState == null) {
            FragmentTransaction transaction = mFm.beginTransaction();
            OneFragment oneFragment = new OneFragment();
            mCurrentFragmen = oneFragment;
            transaction.add(R.id.content, oneFragment, mFragmentTagList[0]).commitAllowingStateLoss();
        } else {
            // 通过tag找到fragment并重置
            OneFragment oneFragment = (OneFragment) mFm.findFragmentByTag(mFragmentTagList[0]);
            TwoFragment twoFragment = (TwoFragment) mFm.findFragmentByTag(mFragmentTagList[1]);
            ThreeFragment threeFragment = (ThreeFragment) mFm.findFragmentByTag(mFragmentTagList[2]);
            FourFragment fourFragment = (FourFragment) mFm.findFragmentByTag(mFragmentTagList[3]);
            FiveFragment fiveFragment = (FiveFragment) mFm.findFragmentByTag(mFragmentTagList[4]);
            mFm.beginTransaction().show(oneFragment).hide(twoFragment).hide(threeFragment);
        }
    }

    public static void JumpTMain(Activity activity) {
//        AppUtils.getAppUtilsInstance().finish();
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

}

