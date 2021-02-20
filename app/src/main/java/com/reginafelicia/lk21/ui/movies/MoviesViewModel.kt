package com.reginafelicia.lk21.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reginafelicia.core.domain.usecase.MovieUseCase
import com.reginafelicia.lk21.ui.movies.mapper.toMovieListModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val useCase: MovieUseCase
) :
    ViewModel() {

    private lateinit var subscription: Disposable
    private var _viewState: MutableLiveData<MoviesViewState> = MutableLiveData()

    fun getViewState(): MutableLiveData<MoviesViewState> = _viewState

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun getMovieListData() {
        subscription = useCase.loadMovie()
            .subscribeOn(Schedulers.io())
            .map {
                MoviesViewState.Success(it.toMovieListModel()) as MoviesViewState
            }
            .toObservable()
            .onErrorReturn { MoviesViewState.Failed }
            .startWith(MoviesViewState.Loading)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _viewState.value = it
            }
    }
}