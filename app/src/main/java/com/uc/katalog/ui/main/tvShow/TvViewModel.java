package com.uc.katalog.ui.main.tvShow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.uc.katalog.model.Movie;
import com.uc.katalog.model.TVShow;
import com.uc.katalog.repository.MovieRepository;
import com.uc.katalog.repository.TVRepository;

import java.util.List;

public class TvViewModel extends ViewModel {

    private TVRepository repository;

    public TvViewModel(){
        repository = TVRepository.getInstance();
    }

    public LiveData<List<TVShow>> getTVCollection(){
        return repository.getTVCollection();
    }
}
