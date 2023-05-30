package com.si61.wisatajenni.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//ada tambahan di build.gradle
public class RetroServer {
    private static final String alamatServer = "https://wisatajenni.000webhostapp.com/";
    private static Retrofit retro;

    public static Retrofit kenokRetrofit(){
        if(retro==null){
            retro = new Retrofit.Builder()
                    .baseUrl(alamatServer)
                    .addConverterFactory(GsonConverterFactory.create())//ini utk mengconvert data dari data yg bntk JSON agar dimengerti Andorid Studio
                    .build();
        }

        return retro;
    }
}
