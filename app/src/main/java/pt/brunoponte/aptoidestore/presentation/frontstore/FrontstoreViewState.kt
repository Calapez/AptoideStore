package pt.brunoponte.aptoidestore.presentation.frontstore

data class FrontstoreViewState (
    val isLoading: Boolean = false,
    val message: String? = null,
    val apps: List<AppItemUiModel>? = null,
)
