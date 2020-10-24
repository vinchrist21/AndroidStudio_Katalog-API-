package com.uc.katalog.network;

import com.uc.katalog.model.CastResponse;
import com.uc.katalog.model.GenreResponse;
import com.uc.katalog.model.MovieResponse;
import com.uc.katalog.model.TVResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndpoints {

    @GET("discover/movie")
    Call<MovieResponse> getMovies(@Query("api_key")String apiKey);

    @GET("discover/tv")
    Call<TVResponse> getTVs(@Query("api_key")String apiKey);

    @GET("movie/{movie_id}")
    Call<GenreResponse> getGenres(@Path("id")int id, @Query("api_key")String apiKey);

    @GET("{type}/{id}")
    Call<GenreResponse> getGenres(@Path("type") String type, @Path("id") int id, @Query("api_key") String apiKey);

    @GET("{type}/{id}/credits")
    Call<CastResponse> getCasts(@Path("type") String type, @Path("id") int id, @Query("api_key") String apiKey);


    //    @GET("movie/{movie_id}")
//    Call<MovieResponse> getDetailMovie(@Path("movie_id")int movieid, @Query("api_key")String apiKey);
}
