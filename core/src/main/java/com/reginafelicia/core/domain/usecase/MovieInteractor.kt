package com.reginafelicia.core.domain.usecase

import com.reginafelicia.core.data.source.database.model.DetailModel
import com.reginafelicia.core.domain.model.MovieDetailResponse
import com.reginafelicia.core.domain.model.MovieResponse
import com.reginafelicia.core.domain.model.TvShowsDetailResponse
import com.reginafelicia.core.domain.model.TvShowsResponse
import com.reginafelicia.core.domain.repository.IMovieRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class MovieInteractor @Inject constructor(val movieRespository: IMovieRepository) : MovieUseCase {

    override fun loadMovie(): Single<MovieResponse> = movieRespository.loadMovie()

    override fun loadTvShows(): Single<TvShowsResponse> = movieRespository.loadTvShows()

    override fun loadDetailMovie(id: Int): Single<MovieDetailResponse> =
        movieRespository.loadDetailMovie(id)

    override fun loadDetailTvShows(id: Int): Single<TvShowsDetailResponse> =
        movieRespository.loadDetailTvShows(id)

    override fun getFavorite(id: Int): Observable<DetailModel?> = movieRespository.getFavorite(id)

    override fun insertFavorite(movie: DetailModel) = movieRespository.insertFavorite(movie)

    override fun deleteFavorite(movie: DetailModel) = movieRespository.deleteFavorite(movie)

    override fun getAllFavoriteTvShow() = movieRespository.getAllFavoriteTvShow()

    override fun getAllFavoriteMovie() = movieRespository.getAllFavoriteMovie()

}