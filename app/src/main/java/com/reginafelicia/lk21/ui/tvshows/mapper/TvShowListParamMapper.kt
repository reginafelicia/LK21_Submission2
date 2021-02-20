package com.reginafelicia.lk21.ui.tvshows.mapper

import com.reginafelicia.core.domain.model.TvShowsResponse
import com.reginafelicia.lk21.ui.tvshows.model.TvShowsListModel


fun TvShowsResponse.toTvShowListModel(): List<TvShowsListModel> {
    return this.results.map {
        TvShowsListModel(
            id = it.id,
            title = it.original_name,
            image_url = it.poster_path
        )
    }
}