package com.dimpossitorus.retrofit.api;

import com.dimpossitorus.retrofit.model.GitModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Dimpos Sitorus on 10/06/2016.
 */

public interface GitApi {

    @GET("/users/{user}")
    Call<GitModel> getFeed(@Path("user") String user);

}
