package com.example.api_k4;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IAlbumAPI {
    @GET("album")
    @Headers({"Content-Type:application/json"})
    Call<List<AlbumEntity>> getAllAlbum();

    @GET("album/{id}")
    @Headers({"Content-Type:application/json"})
    Call<AlbumEntity> getAllALbumByID(@Path("id") String id);

    @POST("album")
    @Headers({"Content-Type:application/json"})
    Call<Object> createAlbum(@Body AlbumEntity albumEntity);
    //@Field("id") String id,
    //                                  @Field("name") String name,
    //                                  @Field("description") String desc,
    //                                  @Field("numberPic") int number,
    //                                  @Field("date") String date
}
