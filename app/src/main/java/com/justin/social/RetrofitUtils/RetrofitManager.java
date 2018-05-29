package com.justin.social.RetrofitUtils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

public class RetrofitManager {

//    private static String SOUND_CLOUD_BASE_URL = "http://www.youxuanzhijia.top/app-api/";
    private static String SOUND_CLOUD_BASE_URL = "http://39.107.72.34:8080/app-api/";

    public static Retrofit getSoundCloudRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request origin = chain.request();
                HttpUrl url = origin.url().newBuilder().
                        build();
                return chain.proceed(origin.newBuilder().url(url).build());
            }
        });
        return new Retrofit.Builder()
                .baseUrl(SOUND_CLOUD_BASE_URL)
                .addConverterFactory(DecodeConverterFactory.create())
                .client(builder.build())
                .build();
    }

}
