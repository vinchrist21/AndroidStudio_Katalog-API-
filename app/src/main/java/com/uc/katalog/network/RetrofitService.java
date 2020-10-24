package com.uc.katalog.network;

import com.uc.katalog.model.CastResponse;
import com.uc.katalog.model.GenreResponse;
import com.uc.katalog.model.MovieResponse;
import com.uc.katalog.model.TVResponse;
import com.uc.katalog.util.Constants;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

//    private static Retrofit retrofit;
//
//    public static <S> S createService(Class<S> serviceClass){
//        if (retrofit == null){
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(Constants.BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit.create(serviceClass);
//    }

    private ApiEndpoints api;
    private static RetrofitService service;

    private RetrofitService() {
        api = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                .create(ApiEndpoints.class);
    }

    public static RetrofitService getInstance(){
        if (service==null){
            service = new RetrofitService();
        }
        return service;
    }

    public Call<MovieResponse> getMovies(){
        return api.getMovies(Constants.API_KEY);
    }

    public Call<TVResponse> getTVs(){
        return api.getTVs(Constants.API_KEY);
    }

    public Call<GenreResponse> getGenres(String type, int id){
        return api.getGenres(type, id, Constants.API_KEY);
    }

    public Call<CastResponse> getCasts(String type, int id){
        return api.getCasts(type, id, Constants.API_KEY);
    }
}
