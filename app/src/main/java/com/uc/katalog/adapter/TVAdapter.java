package com.uc.katalog.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uc.katalog.R;
import com.uc.katalog.model.Movie;
import com.uc.katalog.model.TVShow;
import com.uc.katalog.ui.main.tvShow.TvShowFragment;
import com.uc.katalog.ui.main.tvShow.TvShowFragmentDirections;

import java.util.ArrayList;
import java.util.List;

public class TVAdapter extends RecyclerView.Adapter<TVAdapter.CardViewViewHolder>{

    private Context context;
    private List<TVShow> listTVshow;
    private List<TVShow> getListTVshow() {
        return listTVshow;
    }
    public void setListTVshow(List<TVShow> listTVshow) {
        this.listTVshow = listTVshow;
    }
    public TVAdapter(Context context) {
        this.listTVshow = new ArrayList<TVShow>();
        this.context = context;
    }

    @NonNull
    @Override
    public TVAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new TVAdapter.CardViewViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final TVAdapter.CardViewViewHolder holder, int position) {
        final TVShow tvShow = getListTVshow().get(position);
        Glide.with(context).load(tvShow.getPoster()).centerCrop().into(holder.img);
        holder.lbl_title.setText(tvShow.getName());
        holder.lbl_vote.setText(tvShow.getVote());
        holder.itemView.setOnClickListener(view -> {
            TvShowFragmentDirections.ActionFromTvToDetail action = TvShowFragmentDirections.actionFromTvToDetail(null,tvShow);
            Navigation.findNavController(view).navigate(action);
        });
        Log.d("TVShow",tvShow.getPoster());
    }

    @Override
    public int getItemCount() {
        return getListTVshow().size();
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

