package com.trial.moviehouse.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.filter
import com.trial.moviehouse.data.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@HiltViewModel
class MoviesListViewModel @Inject constructor(
    pager: Pager<Int, Movie>
) : ViewModel() {
    val moviePagingFlow = pager.flow.map {
        val movieMap = mutableSetOf<Int>()
        it.filter { movie ->
            if (movieMap.contains(movie.id)) {
                false
            } else {
                movieMap.add(movie.id)
            }
        }
    }.cachedIn(viewModelScope)
}
