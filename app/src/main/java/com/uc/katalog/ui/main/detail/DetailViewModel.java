package com.uc.katalog.ui.main.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.uc.katalog.model.Cast;
import com.uc.katalog.model.Genre;
import com.uc.katalog.repository.MovieRepository;
import com.uc.katalog.repository.TVRepository;

import java.util.List;

public class DetailViewModel extends ViewModel {

    private MovieRepository movieRepository;
    private TVRepository tvRepository;

    public DetailViewModel(){
        movieRepository = MovieRepository.getInstance();
        tvRepository = TVRepository.getInstance();
    }

    public LiveData<List<Genre>> getMovieGenre(int id){
        return movieRepository.getGenres(id);
    }

    public LiveData<List<Genre>> getTVShowGenre(int id){
        return tvRepository.getGenres(id);
    }

    public LiveData<List<Cast>> getTVShowCast(int id){
        return tvRepository.getCasts(id);
    }

    public LiveData<List<Cast>> getMovieCast(int id){
        return movieRepository.getCasts(id);
    }

}
