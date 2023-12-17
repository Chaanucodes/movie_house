package com.trial.moviehouse.ui.movie

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.trial.moviehouse.R
import com.trial.moviehouse.data.models.Movie
import com.trial.moviehouse.databinding.MovieListItemBinding
import com.trial.moviehouse.util.Constants
import com.trial.moviehouse.util.getReleaseDate


class MoviesListAdapter(
    val onClick: (movie: Movie?) -> Unit
) : PagingDataAdapter<Movie, MoviesListAdapter.MoviesViewHolder>(Movie.DIFF_CALLBACK) {

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
            text = getItem(position)?.title
        }

        holder.binding.root.setOnClickListener {
            onClick(getItem(position))
        }

        val releaseDate = getItem(position)?.releaseDate
        val parsedReleaseDate = if (releaseDate.isNullOrEmpty()) {
            "Release Date: \nN/A"
        }else{
            "Release Date: \n" + getReleaseDate(getItem(position)?.releaseDate)
        }

        holder.binding.tvMovieReleaseDate.text = parsedReleaseDate

        Glide.with(holder.binding.root.context)
            .load(Constants.IMAGE_BASE_URL + getItem(position)?.posterPath)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.error_image)

            )
            .into(holder.binding.ivMoviePoster)
    }


    class MoviesViewHolder(val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}