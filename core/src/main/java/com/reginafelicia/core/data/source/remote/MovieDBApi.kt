package com.reginafelicia.core.data.source.remote

import com.reginafelicia.core.domain.model.MovieDetailResponse
import com.reginafelicia.core.domain.model.MovieResponse
import com.reginafelicia.core.domain.model.TvShowsDetailResponse
import com.reginafelicia.core.domain.model.TvShowsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBApi {

    @GET("movie/now_playing")
    fun getMovieList(
        @Query("api_key") API_KEY: String = "6ffc29a38d5032bd5b0570e01c5132b0",
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<MovieResponse>

    @GET("tv/airing_today")
    fun getTvShowList(
        @Query("api_key") API_KEY: String = "6ffc29a38d5032bd5b0570e01c5132b0",
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<TvShowsResponse>

    @GET("movie/{id}")
    fun getDetail(
        @Path("id") Id: Int,
        @Query("api_key") API_KEY: String = "6ffc29a38d5032bd5b0570e01c5132b0",
        @Query("language") language: String
    ): Single<MovieDetailResponse>

    @GET("tv/{tv_id}")
    fun getTvShowsDetail(
        @Path("tv_id") Id: Int,
        @Query("api_key") API_KEY: String = "6ffc29a38d5032bd5b0570e01c5132b0",
        @Query("language") language: String
    ): Single<TvShowsDetailResponse>

}