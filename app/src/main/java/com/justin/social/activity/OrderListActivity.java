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
import com.justin.social.RetrofitUtils.DataBean.five.OrderConfig;
import com.justin.social.RetrofitUtils.DataBean.one.NewsListConfig;
import com.justin.social.databinding.ActivityNewslistBinding;
import com.justin.social.databinding.ActivityOrderlistBinding;
import com.justin.social.fragment.NewsFragmentOne;
import com.justin.social.fragment.OrderFragmentOne;

import java.util.ArrayList;

public class OrderListActivity extends BackActivity {
    public static final String INDEX_TYPE = "index_type";
    public static final String INDEX_ORDER_ONE      = "index_order_one";
    public static final String INDEX_ORDER_TWO      = "index_order_two";
    public static final String INDEX_ORDER_THREE    = "index_order_three";
    public static final String INDEX_ORDER_FOUR     = "index_order_four";
    ActivityOrderlistBinding bind;
    private FragmentManager mFm;
    private ArrayList<Fragment> mFragmentList = new ArrayList<Fragment>();
    private String[] mFragmentTagList = {"orderOne", "orderTwo", "orderThree", "orderThree"};
    private Fragment mCurrentFragmen = null;
    TextView textView;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView line;
    TextView line1;
    TextView line2;
    TextView line3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this,R.layout.activity_orderlist);
        initView();
        initData();
    }

    private void initView() {
        textView = (TextView) findViewById(R.id.hot_tv);
        textView1 = (TextView) findViewById(R.id.social_tv);
        textView2 = (TextView) findViewById(R.id.new_tv);
        textView3 = (TextView) findViewById(R.id.allorder_tv);
        line = (TextView) findViewById(R.id.hot_line);
        line1 = (TextView) findViewById(R.id.social_line);
        line2 = (TextView) findViewById(R.id.new_line);
        line3 = (TextView) findViewById(R.id.allorder_line);
    }

    private void initData() {
        Bundle bundle = new Bundle();
        Intent intent = getIntent();
        bundle.putSerializable(OrderFragmentOne.ORDER_TYPE_ONE,intent.getSerializableExtra(INDEX_ORDER_ONE));
        bundle.putString(OrderFragmentOne.TYPE, OrderConfig.NO_PAY);

        Bundle bundle1 = new Bundle();
        bundle1.putSerializable(OrderFragmentOne.ORDER_TYPE_ONE, intent.getSerializableExtra(INDEX_ORDER_TWO));
        bundle1.putString(OrderFragmentOne.TYPE, OrderConfig.HAVE_PAY);

        Bundle bundle2 = new Bundle();
        bundle2.putSerializable(OrderFragmentOne.ORDER_TYPE_ONE, intent.getSerializableExtra(INDEX_ORDER_THREE));
        bundle2.putString(OrderFragmentOne.TYPE, OrderConfig.DURPAY);

        Bundle bundle3 = new Bundle();
        bundle3.putSerializable(OrderFragmentOne.ORDER_TYPE_ONE, intent.getSerializableExtra(INDEX_ORDER_FOUR));
        bundle3.putString(OrderFragmentOne.TYPE, OrderConfig.ALL_PAY);
        //fragment保存参数，传入一个Bundle对象

        OrderFragmentOne oneFragment = new OrderFragmentOne();
        OrderFragmentOne twoFragment = new OrderFragmentOne();
        OrderFragmentOne threeFragment = new OrderFragmentOne();
        OrderFragmentOne fourFragment = new OrderFragmentOne();
        oneFragment.setArguments(bundle);
        twoFragment.setArguments(bundle1);
        threeFragment.setArguments(bundle2);
        fourFragment.setArguments(bundle3);
        mFragmentList.add(0, oneFragment);
        mFragmentList.add(1, twoFragment);
        mFragmentList.add(2, threeFragment);
        mFragmentList.add(3, fourFragment);
        mCurrentFragmen = mFragmentList.get(0);
        // 初始化首次进入时的Fragment
        mFm = getFragmentManager();
        FragmentTransaction transaction = mFm.beginTransaction();
        transaction.add(R.id.content, mCurrentFragmen, mFragmentTagList[0]);
        transaction.commitAllowingStateLoss();
        int index = getIntent().getIntExtra(INDEX_TYPE,0);
        if(index!=0){
            switch (index){
                case 1:
                    onSocialClick(null);
                    break;
                case 2:
                    onNewsClick(null);
                    break;
                case 3:
                    onAllClick(null);
                    break;
            }
        }

    }

    public void onHotClick(View view){
        textView.setTextColor(getResources().getColor(R.color.text_green));
        line.setBackgroundColor(getResources().getColor(R.color.text_green));

        textView1.setTextColor(getResources().getColor(R.color.text_a7));
        line1.setBackgroundColor(getResources().getColor(R.color.white));

        textView2.setTextColor(getResources().getColor(R.color.text_a7));
        line2.setBackgroundColor(getResources().getColor(R.color.white));

        textView3.setTextColor(getResources().getColor(R.color.text_a7));
        line3.setBackgroundColor(getResources().getColor(R.color.white));
        switchFragment( mFragmentList.get(0),mFragmentTagList[0]);
    }

    public void onSocialClick(View view){
        textView.setTextColor(getResources().getColor(R.color.text_a7));
        line.setBackgroundColor(getResources().getColor(R.color.white));

        textView1.setTextColor(getResources().getColor(R.color.text_green));
        line1.setBackgroundColor(getResources().getColor(R.color.text_green));

        textView2.setTextColor(getResources().getColor(R.color.text_a7));
        line2.setBackgroundColor(getResources().getColor(R.color.white));

        textView3.setTextColor(getResources().getColor(R.color.text_a7));
        line3.setBackgroundColor(getResources().getColor(R.color.white));
        switchFragment( mFragmentList.get(1),mFragmentTagList[1]);
    }

    public void onNewsClick(View view){
        textView.setTextColor(getResources().getColor(R.color.text_a7));
        line.setBackgroundColor(getResources().getColor(R.color.white));

        textView1.setTextColor(getResources().getColor(R.color.text_a7));
        line1.setBackgroundColor(getResources().getColor(R.color.white));

        textView2.setTextColor(getResources().getColor(R.color.text_green));
        line2.setBackgroundColor(getResources().getColor(R.color.text_green));

        textView3.setTextColor(getResources().getColor(R.color.text_a7));
        line3.setBackgroundColor(getResources().getColor(R.color.white));
        switchFragment( mFragmentList.get(2),mFragmentTagList[2]);
    }

    public void onAllClick(View view){
        textView.setTextColor(getResources().getColor(R.color.text_a7));
        line.setBackgroundColor(getResources().getColor(R.color.white));

        textView1.setTextColor(getResources().getColor(R.color.text_a7));
        line1.setBackgroundColor(getResources().getColor(R.color.white));

        textView2.setTextColor(getResources().getColor(R.color.text_a7));
        line2.setBackgroundColor(getResources().getColor(R.color.white));

        textView3.setTextColor(getResources().getColor(R.color.text_green));
        line3.setBackgroundColor(getResources().getColor(R.color.text_green));
        switchFragment( mFragmentList.get(3),mFragmentTagList[3]);
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

    public static void JumpToOrderActivity(Context context,OrderConfig noPay,OrderConfig havePay
            ,OrderConfig duringPay,OrderConfig allPay,int index){
            Intent intent = new Intent(context,OrderListActivity.class);
            intent.putExtra(INDEX_TYPE,index);
            intent.putExtra(INDEX_ORDER_ONE,noPay);
            intent.putExtra(INDEX_ORDER_TWO,havePay);
            intent.putExtra(INDEX_ORDER_THREE,duringPay);
            intent.putExtra(INDEX_ORDER_FOUR,allPay);
            context.startActivity(intent);
    }
}
