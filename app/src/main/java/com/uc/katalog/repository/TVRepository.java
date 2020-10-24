package com.uc.katalog.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.uc.katalog.model.Cast;
import com.uc.katalog.model.CastResponse;
import com.uc.katalog.model.Genre;
import com.uc.katalog.model.GenreResponse;
import com.uc.katalog.model.TVShow;
import com.uc.katalog.model.TVResponse;
import com.uc.katalog.network.RetrofitService;
import com.uc.katalog.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVRepository {
    private static TVRepository tvRepository;
    private RetrofitService service;
    private static final String TAG = "TVRepository";

    private TVRepository() {
        service = RetrofitService.getInstance();
    }

    public static TVRepository getInstance(){
        if (tvRepository == null){
            tvRepository = new TVRepository();
        }
        return tvRepository;
    }

    public MutableLiveData<List<TVShow>> getTVCollection(){
        MutableLiveData<List<TVShow>> listTV = new MutableLiveData<>();

        service.getTVs().enqueue(new Callback<TVResponse>() {
            @Override
            public void onResponse(Call<TVResponse> call, Response<TVResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body()!= null){
                        listTV.postValue(response.body().getResults());
                    }
                }
            }

            @Override
            public void onFailure(Call<TVResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

        return listTV;
    }

    public MutableLiveData<List<Genre>> getGenres(int id){
        MutableLiveData<List<Genre>> listGenres = new MutableLiveData<>();

        service.getGenres(Constants.Type.MOVIES, id).enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: " + response.body().getGenres().size());
                        listGenres.postValue(response.body().getGenres());
                    }
                }
            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {

            }
        });
        return listGenres;
    }

    public MutableLiveData<List<Cast>>getCasts(int id){
        MutableLiveData<List<Cast>> listCasts = new MutableLiveData<>();

        service.getCasts(Constants.Type.TVSHOWS, id).enqueue(new Callback<CastResponse>() {
            @Override
            public void onResponse(Call<CastResponse> call, Response<CastResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: " + response.body().getCast().size());
                        listCasts.postValue(response.body().getCast());
                    }
                }
            }

            @Override
            public void onFailure(Call<CastResponse> call, Throwable t) {

            }
        });
        return listCasts;
    }
}
