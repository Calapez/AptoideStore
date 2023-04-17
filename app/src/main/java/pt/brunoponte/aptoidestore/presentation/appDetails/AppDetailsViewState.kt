package pt.brunoponte.aptoidestore.presentation.appDetails


sealed class AppDetailsViewState {

    object Loading : AppDetailsViewState()
    data class Error(val errorMsg: String) : AppDetailsViewState()
    data class Content(val app: AppDetailsUiModel) : AppDetailsViewState()

}
