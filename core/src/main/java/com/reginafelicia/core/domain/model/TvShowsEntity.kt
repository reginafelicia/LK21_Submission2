package com.reginafelicia.core.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TvShowsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val original_name: String,
    val vote_average: Float,
    val overview: String,
    val first_air_date: String,
    val poster_path: String
)