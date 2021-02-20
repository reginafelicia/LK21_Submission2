package com.reginafelicia.core.data.source.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lk21")
data class DetailModel(
    @PrimaryKey
    val id: Int,
    val title: String,
    val date: String,
    val description: String,
    val category: String,
    val image: String,
    val type: String
)

enum class Type {
    MOVIE,
    TV_SHOW
}
