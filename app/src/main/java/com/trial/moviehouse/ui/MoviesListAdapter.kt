package com.trial.moviehouse.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.trial.moviehouse.data.models.Movie
import com.trial.moviehouse.databinding.MovieListItemBinding


class MoviesListAdapter(private val movies : List<Movie>) : RecyclerView.Adapter<MoviesListAdapter.MoviesViewHolder>(){

    fun updateList(newMovies : List<Movie>){
        movies.toMutableList().addAll(newMovies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            MovieListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.binding.tvMovieTitle.text = movies[position].title
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class MoviesViewHolder(val binding: MovieListItemBinding) :
    RecyclerView.ViewHolder(binding.root)

}