package pt.brunoponte.aptoidestore.data.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime


@Entity(tableName = "apps")
data class AppEntity (
    @PrimaryKey @ColumnInfo(name = "id") var id: Long,
    @PrimaryKey @ColumnInfo(name = "name")  var name: String?,
    @PrimaryKey @ColumnInfo(name = "appPackage") var appPackage: String?,
    @PrimaryKey @ColumnInfo(name = "storeId") var storeId: Long?,
    @PrimaryKey @ColumnInfo(name = "storeName") var storeName: String?,
    @PrimaryKey @ColumnInfo(name = "versionName") var versionName: String?,
    @PrimaryKey @ColumnInfo(name = "versionCode") var versionCode: Long?,
    @PrimaryKey @ColumnInfo(name = "md5sum") var md5sum: String?,
    @PrimaryKey @ColumnInfo(name = "apkTags") var apkTags: List<String>?,
    @PrimaryKey @ColumnInfo(name = "size") var size: Long?,
    @PrimaryKey @ColumnInfo(name = "downloads") var downloads: Long?,
    @PrimaryKey @ColumnInfo(name = "pDownloads") var pDownloads: Long?,
    @PrimaryKey @ColumnInfo(name = "added") var added: LocalDateTime?,
    @PrimaryKey @ColumnInfo(name = "modified") var modified: LocalDateTime?,
    @PrimaryKey @ColumnInfo(name = "updated") var updated: LocalDateTime?,
    @PrimaryKey @ColumnInfo(name = "rating") var rating: Float?,
    @PrimaryKey @ColumnInfo(name = "iconUrl") var iconUrl: String?,
    @PrimaryKey @ColumnInfo(name = "graphicUrl") var graphicUrl: String?,
    @PrimaryKey @ColumnInfo(name = "upType") var upType: String?
)