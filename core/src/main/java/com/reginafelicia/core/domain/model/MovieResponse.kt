package com.reginafelicia.core.domain.model


data class MovieResponse(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<MovieEntity>
)