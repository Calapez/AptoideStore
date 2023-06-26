package pt.brunoponte.aptoidestore.presentation.frontstore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _viewState = MutableStateFlow<FrontstoreViewState>(FrontstoreViewState.Loading)
    val viewState: StateFlow<FrontstoreViewState>
        get() = _viewState

    fun getApps() {
        viewModelScope.launch(dispatcher) {
            _viewState.value = FrontstoreViewState.Loading
            val response = getAppListUseCase.execute()
            when (response) {
                is Response.Success ->
                    _viewState.value = FrontstoreViewState
                        .Content(response.data.map {
                            AppItemUiModel(
                                it.id,
                                it.name,
                                it.rating,
                                it.graphicUrl,
                                it.iconUrl
                            )
                        })
                is Response.Error ->
                    _viewState.value = FrontstoreViewState
                        .Error(response.exception.message ?: "")
            }
        }
    }

}