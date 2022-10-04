package com.cristopher.guaman.proyectotfc.interfaces;

import com.cristopher.guaman.proyectotfc.models.Practicantes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PracticantesAPI {
    @GET("api/listAlumnosF")
    Call <List<Practicantes>> getStudents();

    @GET("api/listAlumnosF/{id}")
    public Call<Practicantes> find(@Path("id") String id);
}
