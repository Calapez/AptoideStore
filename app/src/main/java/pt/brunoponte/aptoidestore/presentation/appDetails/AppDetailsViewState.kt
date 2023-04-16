package pt.brunoponte.aptoidestore.presentation.appDetails

import pt.brunoponte.aptoidestore.domain.models.App

sealed class AppDetailsViewState {

    object Loading : AppDetailsViewState()
    data class Error(val errorMsg: String) : AppDetailsViewState()
    data class Content(val app: AppDetailsUiModel?) : AppDetailsViewState()

}
