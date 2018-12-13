package com.qakap.tunas.tunasqakap.client;

import com.qakap.tunas.tunasqakap.model.BeritaModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @Headers({
            "Content-Type: application/json"
    })

    @GET("get-post")
    Call<ArrayList<BeritaModel>> getBerita();

    @FormUrlEncoded
    @POST("insert-post")
    Call<BeritaModel> postBerita(
            @Field("category") int category_id,
            @Field("name") String name,
            @Field("description") String description,
            @Field("author") String author

    );


}
