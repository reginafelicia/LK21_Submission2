package com.reginafelicia.favorite_movie.di

import android.content.Context
import com.reginafelicia.core.di.ServiceModule
import com.reginafelicia.favorite_movie.ui.FavoriteMovieFragment
import com.reginafelicia.favorite_movie.ui.FavoriteTvShowsFragment
import com.reginafelicia.lk21.core.di.ApplicationComponent
import com.reginafelicia.lk21.core.di.getApplicationComponent
import com.reginafelicia.lk21.core.di.module.ApplicationModule
import com.reginafelicia.lk21.core.di.module.DatabaseModule
import dagger.Component
import javax.inject.Scope

@FavoriteScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FavoriteViewModule::class, ApplicationModule::class, DatabaseModule::class, ServiceModule::class]
)
interface FavoriteComponent {

    fun injectFavoriteTvShowsList(favoriteTvShowsFragment: FavoriteTvShowsFragment)
    fun injectFavoriteMovieList(favoriteMovieFragment: FavoriteMovieFragment)
}


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FavoriteScope

object FavoriteAppComponent {

    fun getFavoriteComponent(context: Context): FavoriteComponent {
        return DaggerFavoriteComponent.builder().applicationComponent(getApplicationComponent())
            .applicationModule(ApplicationModule(context)).build()
    }

}