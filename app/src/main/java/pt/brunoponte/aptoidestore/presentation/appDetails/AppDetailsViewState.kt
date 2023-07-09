package pt.brunoponte.aptoidestore.presentation.appDetails


data class AppDetailsViewState (
    val isLoading: Boolean = false,
    val message: String? = null,
    val app: AppDetailsUiModel? = null,
)
