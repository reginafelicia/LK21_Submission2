package com.reginafelicia.core.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val vote_average: Float,
    val overview: String,
    val release_date: String,
    val video: Boolean,
    val poster_path: String,
    val original_title: String
)