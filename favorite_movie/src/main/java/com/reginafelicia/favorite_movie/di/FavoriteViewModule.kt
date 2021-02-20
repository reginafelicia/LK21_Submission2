package com.reginafelicia.favorite_movie.di

import androidx.lifecycle.ViewModel
import com.reginafelicia.core.di.ViewModelKey
import com.reginafelicia.favorite_movie.ui.FavoriteViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class FavoriteViewModule {

    @FavoriteScope
    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun postProductFavoriteViewModel(viewModel: FavoriteViewModel): ViewModel
}