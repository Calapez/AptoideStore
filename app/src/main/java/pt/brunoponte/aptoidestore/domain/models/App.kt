package pt.brunoponte.aptoidestore.domain.models

import java.time.LocalDateTime
import java.util.*

data class App (
    var id: Long,
    var name: String?,
    var appPackage: String?,
    var storeId: Long?,
    var storeName: String?,
    var versionName: String?,
    var versionCode: Long?,
    var md5sum: String?,
    var size: Long?,
    var downloads: Long?,
    var pDownloads: Long?,
    var added: LocalDateTime?,
    var modified: LocalDateTime?,
    var updated: LocalDateTime?,
    var rating: Float?,
    var iconUrl: String?,
    var graphicUrl: String?,
    var upType: String?
)