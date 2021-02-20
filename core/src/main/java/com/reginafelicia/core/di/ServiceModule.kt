package com.reginafelicia.core.di

import com.reginafelicia.core.data.source.remote.ApiService
import com.reginafelicia.core.data.source.remote.ApiServiceImpl
import com.reginafelicia.core.data.source.remote.MovieRepository
import com.reginafelicia.core.domain.repository.IMovieRepository
import com.reginafelicia.core.domain.usecase.MovieInteractor
import com.reginafelicia.core.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class ServiceModule {
    @Binds
    abstract fun bindApiService(apiService: ApiServiceImpl): ApiService
    @Binds
    abstract fun bindMovieUseCase(interactor: MovieInteractor): MovieUseCase
    @Binds
    abstract fun bindMovieRepository(repo: MovieRepository): IMovieRepository
}