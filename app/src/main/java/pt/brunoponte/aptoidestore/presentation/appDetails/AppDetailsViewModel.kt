package pt.brunoponte.aptoidestore.presentation.appDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.brunoponte.aptoidestore.domain.Response
import pt.brunoponte.aptoidestore.domain.useCases.GetAppDetailsUseCase
import pt.brunoponte.aptoidestore.presentation.frontstore.AppItemUiModel
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel
@Inject
constructor(
    private val getAppDetailsUseCase: GetAppDetailsUseCase,
) : ViewModel() {

    private val _viewState = MutableLiveData<AppDetailsViewState>()
    val viewState: LiveData<AppDetailsViewState>
        get() = _viewState

    fun getAppFromId(appId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            _viewState.postValue(AppDetailsViewState.Loading)
            val response = getAppDetailsUseCase.execute(appId)
            when (response) {
                is Response.Success -> _viewState.postValue(AppDetailsViewState
                    .Content(response.data?.let {
                        AppDetailsUiModel(
                            it.id,
                            it.name,
                            it.size,
                            it.downloads,
                            it.updated,
                            it.rating,
                            it.graphicUrl
                        )
                    }))
                is Response.Error -> _viewState.postValue(AppDetailsViewState
                    .Error(response.exception.message ?: ""))
            }
        }
    }

}