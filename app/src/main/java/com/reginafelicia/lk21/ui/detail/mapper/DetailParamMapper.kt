package com.reginafelicia.lk21.ui.detail.mapper

import com.reginafelicia.core.domain.model.MovieDetailResponse
import com.reginafelicia.core.domain.model.TvShowsDetailResponse
import com.reginafelicia.core.data.source.database.model.DetailModel
import com.reginafelicia.core.data.source.database.model.Type

fun MovieDetailResponse.toDetailModel(): DetailModel {
    return DetailModel(
        id = id,
        description = overview,
        image = poster_path,
        date = release_date,
        category = genres.map { it.name }.reduce { acc, s -> "$acc,$s" },
        title = original_title,
        type = Type.MOVIE.name
    )
}

fun TvShowsDetailResponse.toDetailTvShowsModel(): DetailModel {
    return DetailModel(
        id = id,
        description = overview,
        image = poster_path,
        date = first_air_date,
        category = genres.map { it.name }.reduce { acc, s -> "$acc,$s" },
        title = original_name,
        type = Type.TV_SHOW.name
    )
}