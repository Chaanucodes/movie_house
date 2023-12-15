package com.trial.moviehouse.ui.movie_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.trial.moviehouse.R
import com.trial.moviehouse.databinding.FragmentMovieDetailsBinding
import com.trial.moviehouse.util.Constants
import com.trial.moviehouse.util.GenreUtils
import com.trial.moviehouse.util.getReleaseDate

class MovieDetailsFragment : Fragment() {

    private var binding : FragmentMovieDetailsBinding? = null
    private val args : MovieDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.movie.let {movie->
            binding?.let {
                it.tvMovieTitle.text = movie.title
                val roundedNumber = String.format("%.1f", movie.voteAverage)
                val voteAverage = "Rating: $roundedNumber"
                it.tvMovieRating.text = voteAverage

                val releaseDate = "Release Date: " + getReleaseDate(movie.releaseDate)
                it.tvMovieReleaseDate.text = releaseDate

                it.tvMovieOverview.text = "Description: \n" + movie.overview
                val genres = "Genres : ${GenreUtils.getGenreName(movie.genres)}"
                it.tvMovieGenres.text = genres
                movie.backdropPath?.let { it1 -> setMovieImages(it1, it.ivMovieBackdrop) }
            }

        }
    }

    private fun setMovieImages(path : String, iv: ImageView){
        binding?.let {
            Glide.with(it.root.context)
                .load(Constants.IMAGE_BASE_URL + path)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.placeholder_image)
                        .error(R.drawable.error_image)
                )
                .into(iv)
        }
    }


}