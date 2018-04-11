package com.justin.social.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.justin.social.R;
import com.justin.social.RetrofitUtils.DataBean.callBack.BeanConfigCallBack;
import com.justin.social.RetrofitUtils.DataBean.one.NewListBean;
import com.justin.social.RetrofitUtils.DataBean.one.NewsListConfig;
import com.justin.social.RetrofitUtils.HttpConfigManager;

/**
 * Created by ASUS on 2018/4/11.
 */

public class NewsActivity extends BaseActivity {
    public static final String CONTENT = "content";
    WebView view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        view = (WebView) findViewById(R.id.web);
        // 获取程序中的WebView组件
        new HttpConfigManager().getNewsContentConfig(getIntent().getStringExtra(CONTENT), new BeanConfigCallBack<NewListBean>() {
            @Override
            public void onDataResponse(NewListBean bean) {
                String data = bean.getData().txt;
                String html = "<html>"
                        + "<body>"
                        + "图书封面<br>"
                        + "<table width='200' border='1' >"
                        + "<tr>"
                        + "<td><a onclick='alert(\"Java Web开发速学宝典\")' ><img style='margin:10px' src='http://images.china-pub.com/ebook45001-50000/48015/cover.jpg' width='100'/></a></td>"
                        + "<td><a onclick='alert(\"大象--Thinking in UML\")' ><img style='margin:10px' src='http://images.china-pub.com/ebook125001-130000/129881/zcover.jpg' width='100'/></td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td><img style='margin:10px' src='http://images.china-pub.com/ebook25001-30000/27518/zcover.jpg' width='300'/></td>"
                        + "<td><img  style='margin:10px' src='http://images.china-pub.com/ebook30001-35000/34838/zcover.jpg' width='1300'/></td>"
                        + "</tr>" + "</table>" + "</body>" + "</html>";
                view.loadDataWithBaseURL(null, html, "text/html" , "utf-8", null);
//                view.getSettings().setUseWideViewPort(true);
//                view.getSettings().setLoadWithOverviewMode(true);
//                view.setWebChromeClient(new WebChromeClient());

                WebSettings webSettings = view.getSettings();

                webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
                webSettings.setUseWideViewPort(true);//关键点

                webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                webSettings.setLoadWithOverviewMode(true);
                webSettings.setDisplayZoomControls(false);
                webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
                webSettings.setAllowFileAccess(true); // 允许访问文件
                webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
                webSettings.setSupportZoom(true); // 支持缩放



                webSettings.setLoadWithOverviewMode(true);

                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                int mDensity = metrics.densityDpi;
                if (mDensity == 240) {
                    webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
                } else if (mDensity == 160) {
                    webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
                } else if(mDensity == 120) {
                    webSettings.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
                }else if(mDensity == DisplayMetrics.DENSITY_XHIGH){
                    webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
                }else if (mDensity == DisplayMetrics.DENSITY_TV){
                    webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
                }else{
                    webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
                }


/**
 * 用WebView显示图片，可使用这个参数 设置网页布局类型： 1、LayoutAlgorithm.NARROW_COLUMNS ：
 * 适应内容大小 2、LayoutAlgorithm.SINGLE_COLUMN:适应屏幕，内容将自动缩放
 */
                webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
            }
        });

    }

    public static void JumpToNews(Context context,String content){
        Intent intent = new Intent(context,NewsActivity.class);
        intent.putExtra(CONTENT,content);
        context.startActivity(intent);
    }
}
