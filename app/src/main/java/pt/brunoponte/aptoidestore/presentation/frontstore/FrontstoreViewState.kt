package pt.brunoponte.aptoidestore.presentation.frontstore

sealed class FrontstoreViewState {

    object Loading: FrontstoreViewState()
    data class Error(val errorMsg: String): FrontstoreViewState()
    data class Content(val apps: List<AppItemUiModel>): FrontstoreViewState()

}
