package com.justin.social;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ViewFlipper;

import com.justin.social.RetrofitUtils.DataBean.one.SocialPeopleConfig;
import com.justin.social.databinding.ItemSocialPeopleBinding;
import com.justin.social.fragment.FiveFragment;
import com.justin.social.fragment.FourFragment;
import com.justin.social.fragment.OneFragment;
import com.justin.social.fragment.ThreeFragment;
import com.justin.social.fragment.TwoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FragmentManager mFm;
    private ArrayList<Fragment> mFragmentList = new ArrayList<Fragment>();
    private String[] mFragmentTagList = {"OneFragment", "TwoFragment", "ThreeFragment", "fourFragment", "fiveFragment"};
    private Fragment mCurrentFragmen = null;
    private List<String>list;
    private List<SocialPeopleConfig>configs;
    private int count = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        list = new ArrayList<>();
        configs = new ArrayList<>();
        configs.add(new SocialPeopleConfig("1"));
        configs.add(new SocialPeopleConfig("2"));
        configs.add(new SocialPeopleConfig("3"));
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        list.add("11");
        list.add("12");
        list.add("13");
        list.add("14");
        list.add("15");
        ViewFlipper vf = (ViewFlipper) findViewById(R.id.marquee_view);
        ItemSocialPeopleBinding binding =  DataBindingUtil.inflate(LayoutInflater.from(this),R.layout.item_social_people,null,false);
        ItemSocialPeopleBinding binding1 =  DataBindingUtil.inflate(LayoutInflater.from(this),R.layout.item_social_people,null,false);
        ItemSocialPeopleBinding binding2 =  DataBindingUtil.inflate(LayoutInflater.from(this),R.layout.item_social_people,null,false);
        binding.setModel(configs.get(0));
        binding1.setModel(configs.get(1));
        binding2.setModel(configs.get(2));
        vf.addView(binding.getRoot());
        vf.addView(binding1.getRoot());
        vf.addView(binding2.getRoot());
        vf.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                configs.get(count%3).socialTitle.set(list.get(count%list.size()));
                count++;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

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

