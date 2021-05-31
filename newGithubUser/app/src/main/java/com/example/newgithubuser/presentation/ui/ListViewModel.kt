package com.example.newgithubuser.presentation.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newgithubuser.R
import com.example.newgithubuser.domain.GetReposUseCase
import com.example.newgithubuser.domain.RepoItem
import com.example.newgithubuser.presentation.ui.base.BaseViewModel
import com.example.newgithubuser.util.SchedulerProvider
import com.example.newgithubuser.util.StringResourceProvider
import timber.log.Timber
import javax.inject.Inject

data class ViewState (
    val shouldShowLoading: Boolean = false,
    val repoList: List<RepoItem> = emptyList()
)

sealed class ViewEvent {
    data class ShowErrorMessage(val message: String): ViewEvent()
    data class ShowConnectionDialog(val message: String): ViewEvent()
}

class ListViewModel @Inject constructor(
    private val getReposUseCase: GetReposUseCase,
    private val schedulerProvider: SchedulerProvider,
    private val stringProvider: StringResourceProvider
) : BaseViewModel() {

    private var currentViewState = ViewState(shouldShowLoading = true)
    private val _viewState = MutableLiveData<ViewState>().apply {
        Timber.w("HEY HEY-ViewModel-apply - ")
        //basla()
        currentViewState
    }
    val viewState: LiveData<ViewState> = _viewState

    private val _viewEvent = MutableLiveData<ViewEvent>()
    val viewEvent: LiveData<ViewEvent> = _viewEvent

    private var page = 1

    init {
        Timber.w("HEY HEY-ViewModel-init - ")
        basla()
    }

    fun basla() {
        addDisposable(
            getReposUseCase.getCurrentRepos()
                .subscribeOn(schedulerProvider.ioScheduler())
                .observeOn(schedulerProvider.uiScheduler())
                .subscribe(
                    { response ->
                        Timber.w("HEY HEY-ViewModel-onsuccess - %s", response.size)
                        if (response.isEmpty()) {
                            _viewState.postValue(
                                currentViewState.copy(
                                    shouldShowLoading = true
                                )
                            )
                            onLoad()
                        } else {
                            _viewState.postValue(
                                currentViewState.copy(
                                    repoList = response,
                                    shouldShowLoading = false
                                )
                            )
                        }
                    },
                    { error ->
                        Timber.w("HEY HEY-ViewModel-onerror - %s", error.message)
                        _viewEvent.postValue(
                            ViewEvent.ShowErrorMessage(
                                message = stringProvider.get(R.string.generic_error)
                            )
                        )
                    }
                )
        )
    }

    fun loadMore() {
        onLoad()
    }

    private fun onLoad() {
        addDisposable(
            getReposUseCase.getNextPage(page++)
                .subscribeOn(schedulerProvider.ioScheduler())
                .observeOn(schedulerProvider.uiScheduler())
                .doOnError {error ->
                    Timber.w("HEY HEY-onLoad-ERROR- %s", error.message)
                    _viewEvent.postValue(
                        ViewEvent.ShowErrorMessage(
                            message = stringProvider.get(R.string.generic_error)
                        )
                    )
                }
                .subscribe()
        )
    }
}
