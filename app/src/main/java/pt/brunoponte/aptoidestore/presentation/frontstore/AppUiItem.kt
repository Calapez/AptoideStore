package pt.brunoponte.aptoidestore.presentation.frontstore

import java.time.LocalDateTime

data class AppUiItem (
    var id: Long,
    var name: String?,
    var size: Long?,
    var downloads: Long?,
    var updated: LocalDateTime?,
    var rating: Float?,
    var graphicUrl: String?,
)