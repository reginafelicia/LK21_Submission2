package com.reginafelicia.lk21.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.reginafelicia.lk21.R
import com.reginafelicia.core.di.ViewModelFactory
import com.reginafelicia.lk21.core.di.getApplicationComponent
import com.reginafelicia.lk21.ui.movies.model.MovieListModel
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

class MoviesFragment : Fragment() {

    private lateinit var viewModel: MoviesViewModel
    private lateinit var moviesAdapter: MoviesAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        setupViewModel()
        viewModel.getMovieListData()
    }

    private fun setupViewModel() {
        getApplicationComponent().injectMovieList(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[MoviesViewModel::class.java]
        viewModel.run {
            getViewState().observe(viewLifecycleOwner, Observer {
                setupView(it)
            })
        }
    }

    private fun setupRecyclerView() {
        rv_movie_list.layoutManager = GridLayoutManager(context, 3)
        rv_movie_list.setHasFixedSize(true)
        moviesAdapter = MoviesAdapter()
        rv_movie_list.adapter = moviesAdapter
    }

    private fun setupData(movieList: List<MovieListModel>) {
        moviesAdapter.setMovie(movieList)
    }

    private fun setupView(viewState: MoviesViewState?) {
        when (viewState) {
            is MoviesViewState.Loading -> {
                pb_list_movie.visibility = View.VISIBLE
                iv_error_movies.visibility = View.GONE
            }
            is MoviesViewState.Success -> {
                pb_list_movie.visibility = View.GONE
                iv_error_movies.visibility = View.GONE
                setupData(viewState.movieList)
            }
            is MoviesViewState.Failed -> {
                pb_list_movie.visibility = View.GONE
                iv_error_movies.visibility = View.VISIBLE
            }
        }
    }
}