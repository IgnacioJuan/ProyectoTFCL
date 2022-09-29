package com.cristopher.guaman.proyectotfc.interfaces;

import com.cristopher.guaman.proyectotfc.models.Practicantes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class PracticantesAPI {

    @GET("api/listAlumnosF/{id}")
    public Call<Practicantes> find(@Path("id") String id){
        return null;
    }
}
