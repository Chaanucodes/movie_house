package com.trial.moviehouse.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import com.trial.moviehouse.data.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@HiltViewModel
class MoviesListViewModel @Inject constructor(
    pager: Pager<Int, Movie>
) : ViewModel() {
    val moviePagingFlow = pager.flow.cachedIn(viewModelScope)
}
