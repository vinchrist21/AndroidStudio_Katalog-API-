package com.uc.katalog.adapter;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.uc.katalog.R;
import com.uc.katalog.model.Movie;
import com.uc.katalog.ui.main.movie.MovieFragment;
import com.uc.katalog.ui.main.movie.MovieFragmentDirections;
import com.uc.katalog.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.CardViewViewHolder>{

    private Context context;
    private List<Movie> listMovie;
    private List<Movie> getListMovie() {
        return listMovie;
    }
    public void setListMovie(List<Movie> listMovie) {
        this.listMovie = listMovie;
    }
    public MovieAdapter(Context context) {
        this.listMovie = new ArrayList<Movie>();
        this.context = context;
    }

    @NonNull
    @Override
    public MovieAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MovieAdapter.CardViewViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final MovieAdapter.CardViewViewHolder holder, int position) {
        Movie movie = getListMovie().get(position);
        Glide.with(context).load(movie.getPoster()).centerCrop().into(holder.img);
        holder.lbl_title.setText(movie.getTitle());
        holder.lbl_vote.setText(movie.getVote());
        holder.itemView.setOnClickListener(view -> {
            MovieFragmentDirections.ActionToDetailFragment action = MovieFragmentDirections.actionToDetailFragment(movie, null);
            Navigation.findNavController(view).navigate(action);

//            MovieFragment movieFragment = new MovieFragment();
//            Bundle bundle = new Bundle();
//            bundle.putParcelable("movie",movie);
//
//            movieFragment.setArguments(bundle);

        });
        Log.d("Movie",movie.getPoster());
    }

    @Override
    public int getItemCount() {
        return getListMovie().size();
    }


    class CardViewViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView lbl_title;
        TextView lbl_vote;

        CardViewViewHolder(View itemView) {
            super(itemView);
            lbl_title = itemView.findViewById(R.id.lbl_title);
            lbl_vote = itemView.findViewById(R.id.lbl_vote);
            img = itemView.findViewById(R.id.imageViewAdapter);

        }
    }
}
