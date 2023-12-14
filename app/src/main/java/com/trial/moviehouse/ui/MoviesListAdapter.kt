package com.trial.moviehouse.ui

import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.trial.moviehouse.R
import com.trial.moviehouse.data.models.Movie
import com.trial.moviehouse.databinding.MovieListItemBinding
import com.trial.moviehouse.util.Constants


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
        holder.binding.tvMovieTitle.apply {
            isSelected = true
            isSingleLine = true
            ellipsize = TextUtils.TruncateAt.MARQUEE
            text = movies[position].title
        }
        Glide.with(holder.binding.root.context)
            .load(Constants.IMAGE_BASE_URL + movies[position].backdropPath)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.placeholder_image) // Placeholder image
                    .error(R.drawable.error_image) // Error image in case of loading failure
            )
            .into(holder.binding.ivMoviePoster)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class MoviesViewHolder(val binding: MovieListItemBinding) :
    RecyclerView.ViewHolder(binding.root)

}