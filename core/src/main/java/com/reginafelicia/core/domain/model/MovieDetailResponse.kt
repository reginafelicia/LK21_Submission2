package com.reginafelicia.core.domain.model

data class MovieDetailResponse(
    val id: Int,
    val poster_path: String,
    val original_title: String,
    val overview: String,
    val release_date: String,
    val genres: List<GenreEntity>
)
