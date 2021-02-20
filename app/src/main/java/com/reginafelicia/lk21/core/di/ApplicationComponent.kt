package com.reginafelicia.lk21.core.di

import com.reginafelicia.lk21.core.di.module.ApplicationModule
import com.reginafelicia.lk21.core.di.module.DatabaseModule
import com.reginafelicia.core.di.ServiceModule
import com.reginafelicia.lk21.core.di.module.ViewModelModule
import com.reginafelicia.lk21.ui.detail.DetailActivity
import com.reginafelicia.lk21.ui.movies.MoviesFragment
import com.reginafelicia.lk21.ui.tvshows.TvShowsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ViewModelModule::class, ApplicationModule::class, DatabaseModule::class, ServiceModule::class]
)
interface ApplicationComponent {

    fun injectMovieList(movieListFragment: MoviesFragment)
    fun injectTvShowsList(tvShowsFragment: TvShowsFragment)
    fun injectDetail(detailActivity: DetailActivity)
}