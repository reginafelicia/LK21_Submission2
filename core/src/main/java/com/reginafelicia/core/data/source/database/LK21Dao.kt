package com.reginafelicia.core.data.source.database

import androidx.paging.DataSource
import androidx.room.*
import com.reginafelicia.core.data.source.database.model.DetailModel

@Dao
interface LK21Dao {

    @Query("SELECT * FROM lk21 WHERE type = 'TV_SHOW' ")
    fun getAllFavoriteTvShow(): DataSource.Factory<Int, DetailModel>

    @Query("SELECT * FROM lk21 WHERE type = 'MOVIE' ")
    fun getAllFavoriteMovie(): DataSource.Factory<Int, DetailModel>

    @Query("SELECT * FROM lk21 WHERE id=:id")
    fun getFavorite(id: Int): DetailModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(movie: DetailModel)

    @Delete
    fun deleteFavorite(param: DetailModel)
}