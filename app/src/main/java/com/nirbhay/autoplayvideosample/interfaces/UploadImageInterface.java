package com.nirbhay.autoplayvideosample.interfaces;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Lannet1 on 1/30/2019.
 */

public interface UploadImageInterface {
    @Multipart
    @POST("/test/chatappdemo/tiktokimg/index.php")
    Call<UploadObject> uploadFile(@Part MultipartBody.Part file, @Part("video") RequestBody name);
}