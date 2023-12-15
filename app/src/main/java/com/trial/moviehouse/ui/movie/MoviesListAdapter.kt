package com.trial.moviehouse.ui.movie

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.trial.moviehouse.R
import com.trial.moviehouse.data.models.Movie
import com.trial.moviehouse.databinding.MovieListItemBinding
import com.trial.moviehouse.util.Constants
import com.trial.moviehouse.util.getReleaseDate
import java.text.SimpleDateFormat
import java.util.Locale


class MoviesListAdapter(private val movies : List<Movie>,
                        val onClick : (movie: Movie)->Unit) : RecyclerView.Adapter<MoviesListAdapter.MoviesViewHolder>(){

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

        holder.binding.root.setOnClickListener {
            onClick(movies[position])
        }



/*        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = movies[position].releaseDate?.let { sdf.parse(it) }

        val outputFormatter = SimpleDateFormat("d MMM yyyy", Locale.getDefault())*/
        val releaseDate ="Release Date: \n" + getReleaseDate(movies[position].releaseDate)

        holder.binding.tvMovieReleaseDate.text = releaseDate

        Glide.with(holder.binding.root.context)
            .load(Constants.IMAGE_BASE_URL + movies[position].posterPath)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.error_image)
            )
            .into(holder.binding.ivMoviePoster)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class MoviesViewHolder(val binding: MovieListItemBinding) :
    RecyclerView.ViewHolder(binding.root)

}