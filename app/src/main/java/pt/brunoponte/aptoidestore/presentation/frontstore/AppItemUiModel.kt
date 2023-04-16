package pt.brunoponte.aptoidestore.presentation.frontstore

data class AppItemUiModel (
    var id: Long,
    var name: String?,
    var rating: Float?,
    var graphicUrl: String?,
    var iconUrl: String?,
)