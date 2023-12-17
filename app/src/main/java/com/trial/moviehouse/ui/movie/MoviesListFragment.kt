package com.trial.moviehouse.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.trial.moviehouse.databinding.FragmentMoviesListBinding
import com.trial.moviehouse.util.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MoviesListFragment : Fragment() {

    private val viewModel: MoviesListViewModel by viewModels()
    private var binding: FragmentMoviesListBinding? = null
    private var adapter: MoviesListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdapter()
        setUpListeners()


    }

    private fun setUpListeners() {
        binding?.ibSettings?.setOnClickListener { _ ->
            MoviesListFragmentDirections.actionMoviesListFragmentToSettingsFragment().also { navDr ->
                findNavController().navigate(navDr)
            }
        }
    }

    private fun setUpAdapter() {
        adapter = MoviesListAdapter { movie ->
            movie?.let {
                MoviesListFragmentDirections.actionMoviesListFragmentToMovieDetailsFragment(
                    it
                ).also { navDr ->
                    findNavController().navigate(navDr)
                }
            }
        }

        adapter?.addLoadStateListener { loadState ->
            when {
                loadState.prepend is LoadState.Error -> {
                    loadState.prepend as LoadState.Error
                    Log.d("MOVIE_LIST", "loadState.prepend : ${loadState.prepend}")
                }

                loadState.append is LoadState.Error -> {
                    loadState.append as LoadState.Error
                    binding?.root.let { view ->
                        view?.let { v ->
                            v.showSnackBar((loadState.append as LoadState.Error).error.localizedMessage)
                        }
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    loadState.refresh as LoadState.Error
                    Log.d("MOVIE_LIST", "loadState.refresh : ${loadState.refresh}")
                }

                else -> {
                    null
                }
            }
        }
        binding?.rvMoviesList?.adapter = adapter
        binding?.rvMoviesList?.setHasFixedSize(true)
        binding?.rvMoviesList?.setItemViewCacheSize(20)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.moviePagingFlow.collectLatest { pagingData ->
                adapter?.submitData(pagingData)
            }
        }
    }


}