package pt.brunoponte.aptoidestore.presentation.frontstore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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

    private val _viewState = MutableStateFlow(FrontstoreViewState())
    val viewState: StateFlow<FrontstoreViewState>
        get() = _viewState

    fun getApps() {
        viewModelScope.launch(dispatcher) {
            _viewState.update { currentViewState ->
                currentViewState.copy(isLoading = true)
            }
            val response = getAppListUseCase.execute()
            when (response) {
                is Response.Success -> _viewState.update { currentViewState ->
                    currentViewState.copy(
                        isLoading = false,
                        apps = response.data.map {
                            AppItemUiModel(
                                it.id,
                                it.name,
                                it.rating,
                                it.graphicUrl,
                                it.iconUrl
                            )
                        }
                    )
                }
                is Response.Error -> _viewState.update { currentViewState ->
                    currentViewState.copy(
                        isLoading = false,
                        message = response.exception.message
                    )
                }
            }
        }
    }

    fun onMessageClosed() {
        _viewState.update { currentViewState ->
            currentViewState.copy(message = null)
        }
    }


}