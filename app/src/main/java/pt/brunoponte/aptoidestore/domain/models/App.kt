package pt.brunoponte.aptoidestore.domain.models

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
    var apkTags: List<String>?,
    var size: Long?,
    var downloads: Long?,
    var pDownloads: Long?,
    var added: Date?,
    var modified: Date?,
    var updated: Date?,
    var rating: Float?,
    var iconUrl: String?,
    var graphicUrl: String?,
    var upType: String?
)