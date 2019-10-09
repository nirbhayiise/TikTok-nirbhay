package com.nirbhay.autoplayvideosample.interfaces;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface VideoInterface {

    @Multipart
    @POST("/test//chatappdemo/index.php")
    Call<ResultObject> uploadVideoToServer(@Part MultipartBody.Part video);
}
