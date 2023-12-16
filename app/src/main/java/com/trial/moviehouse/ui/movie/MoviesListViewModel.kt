package com.trial.moviehouse.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.trial.moviehouse.data.models.Movie
import com.trial.moviehouse.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val repository: MoviesRepository,
    pager: Pager<Int, Movie>
)
    : ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> get() = _movies

    val moviePagingFlow = pager.flow.cachedIn(viewModelScope)

    fun getMovies() {
        /*viewModelScope.launch {
            repository.getMovies()?.collect { movies ->
                if(movies is List<*>){
                    _movies.value = movies as List<Movie>
                }else{

                }
            }
        }*/
    }


}
