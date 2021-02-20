package com.reginafelicia.lk21.ui.detail

import com.reginafelicia.core.data.source.database.model.DetailModel

sealed class DetailViewState {
    object Loading : DetailViewState()
    data class Success(val movieEntity: DetailModel) : DetailViewState()
    object Failed : DetailViewState()
    object FailedFavorite : DetailViewState()
}