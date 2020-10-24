package com.uc.katalog.ui.main.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uc.katalog.R;
import com.uc.katalog.model.Genre;
import com.uc.katalog.model.Movie;
import com.uc.katalog.model.TVShow;
import com.uc.katalog.ui.MainActivity;
import com.uc.katalog.ui.main.movie.MovieFragment;
import com.uc.katalog.ui.main.movie.MovieFragmentDirections;
import com.uc.katalog.ui.splash.SplashFragmentDirections;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment {

    @BindView(R.id.imageViewAdapter)
    ImageView iv_poster;

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.tv_genre)
    TextView tv_genre;

    @BindView(R.id.tv_description)
    TextView tv_desc;

    @BindView(R.id.tv_vote)
    TextView tv_vote;

    @BindView(R.id.rv_cast)
    RecyclerView rv_cast;

    private Movie movie;
    private TVShow tvShow;
    private DetailViewModel viewModel;
    private CastAdapter adapter;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        Bundle bundle = this.getArguments();
//        if (bundle != null){
//
//        }
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        viewModel = ViewModelProviders.of(requireActivity()).get(DetailViewModel.class);

        rv_cast.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new CastAdapter(getActivity());

        if (getArguments() != null){
            movie = DetailFragmentArgs.fromBundle(getArguments()).getMovie();
            tvShow = DetailFragmentArgs.fromBundle(getArguments()).getTVShow();

            if (movie != null){
                initMovie(movie);
                observeMovieViewModel(Integer.parseInt(movie.getId_movie()));
            } else {
                initTVShow(tvShow);
                observeTvViewModel(Integer.parseInt(tvShow.getId_show()));
            }
        }
    }

    private void observeMovieViewModel(int idShow){
        viewModel.getMovieGenre(idShow).observe(requireActivity(), genres -> {
            if (genres != null){
                for (int i = 0; i < genres.size(); i++){
                    Genre g = genres.get(i);
                    if (i < genres.size() - 1){
                        tv_genre.append(g.getName()+" , ");
                    } else {
                        tv_genre.append(g.getName());
                    }
                }
            }
        });

        viewModel.getMovieCast(idShow).observe(requireActivity(), casts -> {
            if (casts != null){
                adapter.setCastData(casts);
                adapter.notifyDataSetChanged();
                rv_cast.setAdapter(adapter);
            }
        });
    }


    private void observeTvViewModel(int idShow){
        viewModel.getTVShowGenre(idShow).observe(requireActivity(), genres -> {
            if (genres != null){
                for (int i = 0; i < genres.size(); i++){
                    Genre g = genres.get(i);
                    if (i < genres.size() - 1){
                        tv_genre.append(g.getName()+" , ");
                    } else {
                        tv_genre.append(g.getName());
                    }
                }
            }
        });

        viewModel.getTVShowCast(idShow).observe(requireActivity(), casts -> {
            if (casts != null){
                adapter.setCastData(casts);
                adapter.notifyDataSetChanged();
                rv_cast.setAdapter(adapter);
            }
        });
    }

    private void initMovie(Movie movie) {
        Objects.requireNonNull((MainActivity) requireActivity()).getSupportActionBar().setTitle(movie.getTitle());
        Glide.with(getActivity()).load(movie.getPoster()).centerCrop().into(iv_poster);
        tv_title.setText(movie.getTitle());
        tv_vote.setText(movie.getVote());
        tv_desc.setText(movie.getDescription());
    }

    private void initTVShow(TVShow tvShow){
        Objects.requireNonNull((MainActivity) requireActivity()).getSupportActionBar().setTitle(tvShow.getName());
        Glide.with(getActivity()).load(tvShow.getPoster()).into(iv_poster);
        tv_title.setText(tvShow.getName());
        tv_vote.setText(tvShow.getVote());
        tv_desc.setText(tvShow.getDescription());
    }
}