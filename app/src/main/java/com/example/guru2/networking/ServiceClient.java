package com.example.guru2.networking;

import com.example.guru2.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServiceClient {

    @POST("exec")
    @FormUrlEncoded
    Call<LoginResponse> loginGuru(@Field(value = "sheetName", encoded = true) String sheetName,
                                  @Field(value = "action", encoded = true) String action,
                                  @Field(value = "kodeGuru", encoded = true) String kodeGuru,
                                  @Field(value = "pass", encoded = true) String pass);
}
