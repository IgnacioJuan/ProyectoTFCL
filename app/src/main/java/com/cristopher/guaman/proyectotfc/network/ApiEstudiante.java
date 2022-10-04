package com.cristopher.guaman.proyectotfc.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiEstudiante {
    private static Retrofit retrofit;
    public static Retrofit getStudents(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.18.126:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }


}
