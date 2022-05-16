package com.example.formatif2.html;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {


    @GET("0")
    Call<Void> requeteExercice2 ();
}
