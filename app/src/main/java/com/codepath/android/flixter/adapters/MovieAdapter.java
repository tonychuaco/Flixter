package com.codepath.android.flixter.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.android.flixter.DetailActivity;
import com.codepath.android.flixter.R;
import com.codepath.android.flixter.models.Movie;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

  Context context;
  List<Movie> movies;

  public MovieAdapter(Context context, List<Movie> movies) {
    this.context = context;
    this.movies = movies;
  }

  @NonNull
  @NotNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
    Log.d("MovieAdapter", "onCreateViewHolder");
    View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
    return new ViewHolder(movieView);
  }

  @Override
  public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
    Log.d("MovieAdapter", "onBindViewHolder " + position);
    Movie movie = movies.get(position);
    holder.bind(movie);
  }

  @Override
  public int getItemCount() {
    return movies.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    RelativeLayout container;
    TextView tvTitle;
    TextView tvOverview;
    ImageView ivPoster;

    public ViewHolder(@NonNull @NotNull View itemView) {
      super(itemView);
      tvTitle = itemView.findViewById(R.id.tvTitle);
      tvOverview = itemView.findViewById(R.id.tvOverview);
      ivPoster = itemView.findViewById(R.id.ivPoster);
      container = itemView.findViewById(R.id.container);
    }

    public void bind(Movie movie) {
      tvTitle.setText(movie.getTitle());
      tvOverview.setText(movie.getOverview());
      String imageUrl;
      if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
        imageUrl = movie.getBackdropPath();
      } else {
        imageUrl = movie.getPosterPath();
      }

      Glide.with(context).load(imageUrl).into(ivPoster);

      container.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent i = new Intent(context, DetailActivity.class);
          i.putExtra("movie", Parcels.wrap(movie));
          context.startActivity(i);
        }
      });
    }
  }
}
