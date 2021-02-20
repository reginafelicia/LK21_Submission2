package com.reginafelicia.core.domain.repository

import androidx.paging.DataSource
import com.reginafelicia.core.data.source.database.model.DetailModel
import com.reginafelicia.core.domain.model.MovieDetailResponse
import com.reginafelicia.core.domain.model.MovieResponse
import com.reginafelicia.core.domain.model.TvShowsDetailResponse
import com.reginafelicia.core.domain.model.TvShowsResponse
import io.reactivex.Observable
import io.reactivex.Single

interface IMovieRepository {
    fun loadMovie(): Single<MovieResponse>
    fun loadTvShows(): Single<TvShowsResponse>
    fun loadDetailMovie(id: Int): Single<MovieDetailResponse>
    fun loadDetailTvShows(id: Int): Single<TvShowsDetailResponse>
    fun getFavorite(id: Int): Observable<DetailModel?>
    fun insertFavorite(movie: DetailModel)
    fun deleteFavorite(movie: DetailModel)
    fun getAllFavoriteTvShow(): DataSource.Factory<Int, DetailModel>
    fun getAllFavoriteMovie(): DataSource.Factory<Int, DetailModel>
}
