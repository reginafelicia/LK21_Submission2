package com.reginafelicia.core.data.source.remote

import com.reginafelicia.core.data.source.database.LK21Dao
import com.reginafelicia.core.data.source.database.model.DetailModel
import com.reginafelicia.core.domain.model.MovieDetailResponse
import com.reginafelicia.core.domain.model.MovieResponse
import com.reginafelicia.core.domain.model.TvShowsDetailResponse
import com.reginafelicia.core.domain.model.TvShowsResponse
import com.reginafelicia.core.domain.repository.IMovieRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiService,
    private val dao: LK21Dao
) : IMovieRepository {

    override fun loadMovie(): Single<MovieResponse> {
        val service = apiService.create<MovieDBApi>()
        return service.getMovieList(language = "en", page = 1)
    }

    override fun loadTvShows(): Single<TvShowsResponse> {
        val service = apiService.create<MovieDBApi>()
        return service.getTvShowList(language = "en", page = 1)
    }

    override fun loadDetailMovie(id: Int): Single<MovieDetailResponse> {
        val service = apiService.create<MovieDBApi>()
        return service.getDetail(id, language = "en")
    }

    override fun loadDetailTvShows(id: Int): Single<TvShowsDetailResponse> {
        val service = apiService.create<MovieDBApi>()
        return service.getTvShowsDetail(id, language = "en")
    }

    override fun getFavorite(id: Int): Observable<DetailModel?> {
        return Observable.fromCallable { (dao.getFavorite(id)) }
            .subscribeOn(Schedulers.io())
    }

    override fun insertFavorite(movie: DetailModel) {
        dao.insertFavorite(movie)
    }

    override fun deleteFavorite(movie: DetailModel) {
        dao.deleteFavorite(movie)
    }

    override fun getAllFavoriteTvShow() = dao.getAllFavoriteTvShow()

    override fun getAllFavoriteMovie() = dao.getAllFavoriteMovie()
}