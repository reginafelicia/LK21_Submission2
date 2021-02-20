package com.reginafelicia.favorite_movie.ui

import androidx.lifecycle.ViewModel
import androidx.paging.toLiveData
import com.reginafelicia.core.domain.usecase.MovieUseCase
import com.reginafelicia.lk21.utils.PAGE_SIZE
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(usecase: MovieUseCase) :
    ViewModel() {
    var favoriteTvShowList = usecase.getAllFavoriteTvShow().toLiveData(PAGE_SIZE)
    var favoriteMovieList = usecase.getAllFavoriteMovie().toLiveData(PAGE_SIZE)
}
