package com.reginafelicia.lk21.ui.tvshows

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reginafelicia.core.domain.usecase.MovieUseCase
import com.reginafelicia.lk21.ui.tvshows.mapper.toTvShowListModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TvShowsViewModel @Inject constructor(private val useCase: MovieUseCase) :
    ViewModel() {

    private lateinit var subscription: Disposable
    private var _viewState: MutableLiveData<TvShowsViewState> = MutableLiveData()

    fun getViewState(): MutableLiveData<TvShowsViewState> = _viewState

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun getTvShows() {
        subscription = useCase.loadTvShows()
            .subscribeOn(Schedulers.io())
            .map {
                TvShowsViewState.Success(it.toTvShowListModel()) as TvShowsViewState
            }
            .toObservable()
            .onErrorReturn { TvShowsViewState.Failed }
            .startWith(TvShowsViewState.Loading)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _viewState.value = it
            }
    }
}