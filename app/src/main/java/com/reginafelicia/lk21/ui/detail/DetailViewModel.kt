package com.reginafelicia.lk21.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reginafelicia.core.data.source.database.model.DetailModel
import com.reginafelicia.core.domain.usecase.MovieUseCase
import com.reginafelicia.lk21.ui.detail.mapper.toDetailModel
import com.reginafelicia.lk21.ui.detail.mapper.toDetailTvShowsModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val usecase: MovieUseCase
) :
    ViewModel() {

    var movie: DetailModel? = null
    private lateinit var subscription: Disposable
    private var favorite: MutableLiveData<Boolean> = MutableLiveData()
    private var _viewState: MutableLiveData<DetailViewState> = MutableLiveData()

    fun getViewState(): MutableLiveData<DetailViewState> = _viewState

    fun isFavorite(): MutableLiveData<Boolean> = favorite

    fun setFavorite(favorite: Boolean) {
        this.favorite.value = favorite
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun getDetailMovieListData(id: Int) {
        subscription = usecase.loadDetailMovie(id)
            .subscribeOn(Schedulers.io())
            .map {
                movie = it.toDetailModel()
                DetailViewState.Success(it.toDetailModel()) as DetailViewState
            }
            .toObservable()
            .onErrorReturn { DetailViewState.Failed }
            .startWith(DetailViewState.Loading)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _viewState.value = it
            }
    }

    fun getDetailTvShowsListData(id: Int) {
        subscription = usecase.loadDetailTvShows(id)
            .subscribeOn(Schedulers.io())
            .map {
                movie = it.toDetailTvShowsModel()
                DetailViewState.Success(it.toDetailTvShowsModel()) as DetailViewState
            }
            .toObservable()
            .onErrorReturn { DetailViewState.Failed }
            .startWith(DetailViewState.Loading)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _viewState.value = it
            }
    }

    fun updateFavorite() {
        val movieData = movie ?: return
        val favoriteValue = favorite.value ?: false
        subscription = Observable.just(favoriteValue)
            .map { isFav ->
                if (isFav) {
                    usecase.deleteFavorite(movieData)
                } else {
                    usecase.insertFavorite(movieData)
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    favorite.value = !favoriteValue
                }, {
                    DetailViewState.FailedFavorite
                }
            )
    }

    fun checkIsFavorite(id: Int) {
        subscription = usecase.getFavorite(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    favorite.value = true
                },
                {
                    favorite.value = false
                }
            )
    }
}
