package com.reginafelicia.lk21.ui.movies.mapper

import com.reginafelicia.core.domain.model.MovieResponse
import com.reginafelicia.lk21.ui.movies.model.MovieListModel

fun MovieResponse.toMovieListModel(): List<MovieListModel> {
    return this.results.map {
        MovieListModel(
            id = it.id,
            title = it.original_title,
            image_url = it.poster_path
        )
    }
}