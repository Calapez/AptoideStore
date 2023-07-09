package pt.brunoponte.aptoidestore.presentation.appDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pt.brunoponte.aptoidestore.domain.Response
import pt.brunoponte.aptoidestore.domain.useCases.GetAppUseCase
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel
@Inject
constructor(
    private val getAppUseCase: GetAppUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _viewState = MutableStateFlow(AppDetailsViewState())
    val viewState: StateFlow<AppDetailsViewState>
        get() = _viewState

    fun getAppFromId(appId: Long) {
        viewModelScope.launch(dispatcher) {
            _viewState.update { currentViewState ->
                currentViewState.copy(isLoading = true)
            }

            val response = getAppUseCase.execute(appId)

            when (response) {
                is Response.Success -> _viewState.update { currentViewState ->
                    currentViewState.copy(
                        isLoading = false,
                        app = response.data.let {
                            AppDetailsUiModel(
                                it.id,
                                it.name,
                                it.size,
                                it.downloads,
                                it.updated,
                                it.rating,
                                it.graphicUrl
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