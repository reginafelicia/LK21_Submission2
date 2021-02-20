package com.reginafelicia.lk21.ui.tvshows

import com.reginafelicia.lk21.ui.tvshows.model.TvShowsListModel

sealed class TvShowsViewState {
    object Loading : TvShowsViewState()
    data class Success(val tvShowsList: List<TvShowsListModel>) : TvShowsViewState()
    object Failed : TvShowsViewState()
}