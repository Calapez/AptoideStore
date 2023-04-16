package pt.brunoponte.aptoidestore.data.network.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class AppDto (
    @SerializedName("id") var id: Long,
    @SerializedName("name") var name: String?,
    @SerializedName("package") var appPackage: String?,
    @SerializedName("store_id") var storeId: Long?,
    @SerializedName("store_name") var storeName: String?,
    @SerializedName("vername") var versionName: String?,
    @SerializedName("vercode") var versionCode: Long?,
    @SerializedName("md5sum") var md5sum: String?,
    @SerializedName("size") var size: Long?,
    @SerializedName("downloads") var downloads: Long?,
    @SerializedName("pdownloads") var pDownloads: Long?,
    @SerializedName("added") var added: String?,
    @SerializedName("modified") var modified: String?,
    @SerializedName("updated") var updated: String?,
    @SerializedName("rating") var rating: Float?,
    @SerializedName("icon") var iconUrl: String?,
    @SerializedName("graphic") var graphicUrl: String?,
    @SerializedName("uptype") var upType: String?
)