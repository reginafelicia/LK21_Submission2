package com.reginafelicia.core.domain.model

import com.reginafelicia.core.domain.model.GenreEntity


data class TvShowsDetailResponse(
    val first_air_date: String,
    val id: Int,
    val poster_path: String,
    val original_name: String,
    val overview: String,
    val genres: List<GenreEntity>
)