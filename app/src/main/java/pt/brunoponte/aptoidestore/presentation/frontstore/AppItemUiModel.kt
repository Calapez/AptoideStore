package pt.brunoponte.aptoidestore.presentation.frontstore

/**
 * A representation of the App class, with only the necessary attributes for the UI
 */
data class AppItemUiModel (
    var id: Long,
    var name: String?,
    var rating: Float?,
    var graphicUrl: String?,
    var iconUrl: String?,
)