package com.justin.social.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.justin.social.MainActivity;
import com.justin.social.R;
import com.justin.social.accessor.CommonSettingValue;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity implements View.OnClickListener{
    TextView submit;
    ViewPager pager;
    List<ImageView>list;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        if(CommonSettingValue.getIns(this).getFirst()){
            startActivity(new Intent(this, MainActivity.class));
            CommonSettingValue.getIns(this).setFirst(true);
            finish();
        }
        init();
        submit.setOnClickListener(this);
    }

    public void init() {
        submit = (TextView) findViewById(R.id.submit);
        pager = (ViewPager) findViewById(R.id.pager);
        list = new ArrayList<>();
        ImageView imageView = new ImageView(this);
        ImageView imageView2 = new ImageView(this);
        ImageView imageView3 = new ImageView(this);

        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView3.setScaleType(ImageView.ScaleType.FIT_XY);

        imageView2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView3.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        Glide.with(this).load(R.drawable.icon_first_one).into(imageView);
//        imageView2.setBackgroundResource(R.drawable.guide_2);
        Glide.with(this).load(R.drawable.icon_first_two).into(imageView2);
//        Glide.with(this).load(R.drawable.icon_first_three).into(imageView3);
        imageView3.setImageResource(R.drawable.icon_first_three);
        list.add(imageView);
        list.add(imageView2);
        list.add(imageView3);
        adapter = new CustomAdapter();
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                if(position==2){
                    submit.setVisibility(View.VISIBLE);
                }else{
                    submit.setVisibility(View.GONE);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, MainActivity.class));
        CommonSettingValue.getIns(this).setFirst(true);
        finish();
    }


    class CustomAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }
    }
}
