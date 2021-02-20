package com.reginafelicia.lk21.core.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reginafelicia.core.di.ViewModelFactory
import com.reginafelicia.core.di.ViewModelKey
import com.reginafelicia.lk21.ui.detail.DetailViewModel
import com.reginafelicia.lk21.ui.movies.MoviesViewModel
import com.reginafelicia.lk21.ui.tvshows.TvShowsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun postProductFragmentViewModel(viewModel: MoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TvShowsViewModel::class)
    abstract fun postProductFragmentTvShowsViewModel(viewModel: TvShowsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun postProductActivityDetailViewModel(viewModel: DetailViewModel): ViewModel
}