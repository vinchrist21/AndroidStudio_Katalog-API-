package com.uc.katalog.ui.main.tvShow;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.uc.katalog.R;
import com.uc.katalog.adapter.MovieAdapter;
import com.uc.katalog.adapter.TVAdapter;
import com.uc.katalog.model.Movie;
import com.uc.katalog.model.TVShow;
import com.uc.katalog.ui.main.movie.MovieViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowFragment extends Fragment {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private TvViewModel viewModel;
    private TVAdapter adapter;

    public TvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_movie, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        adapter = new TVAdapter(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(getActivity()).get(TvViewModel.class);
        viewModel.getTVCollection().observe(requireActivity(),observeViewModel);



    }

    private Observer<List<TVShow>> observeViewModel = tvShows -> {
        if (tvShows != null){
            //set adapter
            adapter.setListTVshow(tvShows);
            adapter.notifyDataSetChanged();
//                recyclerView.setAdapter(adapter);
            //add adapter to recyclerview
        }
    };
}