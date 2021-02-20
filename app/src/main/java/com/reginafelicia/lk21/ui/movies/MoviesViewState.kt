package com.reginafelicia.lk21.ui.movies

import com.reginafelicia.lk21.ui.movies.model.MovieListModel

sealed class MoviesViewState {
    object Loading : MoviesViewState()
    data class Success(val movieList: List<MovieListModel>) : MoviesViewState()
    object Failed : MoviesViewState()
}