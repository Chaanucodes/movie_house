package com.trial.moviehouse.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.trial.moviehouse.data.db.MoviesDao
import com.trial.moviehouse.data.models.Movie
import com.trial.moviehouse.data.repository.MoviesRepository
import dagger.multibindings.IntKey
import javax.inject.Inject

/*
class LocalPagingSource @Inject constructor(
    private val yourDao: MoviesDao
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        try {
            val nextPageNumber = params.key ?: 1
            val data = yourDao.getMovies().load()

            return LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = if (data.isEmpty()) null else nextPageNumber + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        TODO("Not yet implemented")
    }
}

class NetworkPagingSource @Inject constructor(
    private val yourRepository: MoviesRepository,
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        try {
            // Load your data from the repository using coroutines
            val response = yourRepository.getNetworkMovies(params.key ?: 1)

            return LoadResult.Page(
                data = response,
                prevKey = if (params.key == 1) null else params.key?.minus(1),
                nextKey = if (response.isEmpty()) null else params.key?.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        TODO("Not yet implemented")
    }
}*/
