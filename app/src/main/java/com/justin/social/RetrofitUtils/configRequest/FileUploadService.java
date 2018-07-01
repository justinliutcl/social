package com.justin.social.RetrofitUtils.configRequest;

import com.justin.social.RetrofitUtils.DataBean.BaseConfig;
import com.justin.social.RetrofitUtils.DataBean.five.HeaderImageConfig;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by ASUS on 2018/6/21.
 */

public interface FileUploadService {
    @Multipart
    @POST("tool/upload")
    Call<HeaderImageConfig> upload(@Part("userId") RequestBody description,
                                   @Part MultipartBody.Part file);
}
