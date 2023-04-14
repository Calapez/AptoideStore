package pt.brunoponte.aptoidestore.presentation.frontstore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import pt.brunoponte.aptoidestore.domain.Response
import pt.brunoponte.aptoidestore.domain.useCases.GetAppListUseCase
import javax.inject.Inject

@HiltViewModel
class FrontstoreViewModel
@Inject
constructor(
    private val getAppListUseCase: GetAppListUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _viewState = MutableLiveData<FrontstoreViewState>()
    val viewState: LiveData<FrontstoreViewState>
        get() = _viewState

    fun getApps() {
        viewModelScope.launch(dispatcher) {
            _viewState.postValue(FrontstoreViewState.Loading)
            val response = getAppListUseCase.execute()
            when (response) {
                is Response.Success -> _viewState.postValue(FrontstoreViewState
                    .Content(response.data.map {
                        AppUiItem(
                            it.id,
                            it.name,
                            it.size,
                            it.downloads,
                            it.updated,
                            it.rating,
                            it.graphicUrl
                        )}))
                is Response.Error -> _viewState.postValue(FrontstoreViewState
                    .Error(response.exception.message ?: ""))
            }
        }
    }

}