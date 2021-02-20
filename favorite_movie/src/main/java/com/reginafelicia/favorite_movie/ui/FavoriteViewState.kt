package com.reginafelicia.favorite_movie.ui

import com.reginafelicia.core.data.source.database.model.DetailModel

sealed class FavoriteViewState {
    object Loading : FavoriteViewState()
    data class Success(val movieList: List<DetailModel>) : FavoriteViewState()
    object Failed : FavoriteViewState()
}