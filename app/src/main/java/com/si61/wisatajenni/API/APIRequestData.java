package com.si61.wisatajenni.API;

import com.si61.wisatajenni.Model.ModelResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestData {
    @GET("retrieve.php")
    //Call datanya yg retrofit2
    Call<ModelResponse> ardRetrieve();


}
