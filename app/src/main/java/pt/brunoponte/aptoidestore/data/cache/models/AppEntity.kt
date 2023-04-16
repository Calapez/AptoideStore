package pt.brunoponte.aptoidestore.data.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime


@Entity(tableName = "apps")
data class AppEntity (
    @PrimaryKey @ColumnInfo(name = "id") var id: Long,
    @ColumnInfo(name = "name")  var name: String?,
    @ColumnInfo(name = "appPackage") var appPackage: String?,
    @ColumnInfo(name = "storeId") var storeId: Long?,
    @ColumnInfo(name = "storeName") var storeName: String?,
    @ColumnInfo(name = "versionName") var versionName: String?,
    @ColumnInfo(name = "versionCode") var versionCode: Long?,
    @ColumnInfo(name = "md5sum") var md5sum: String?,
    @ColumnInfo(name = "size") var size: Long?,
    @ColumnInfo(name = "downloads") var downloads: Long?,
    @ColumnInfo(name = "pDownloads") var pDownloads: Long?,
    @ColumnInfo(name = "added") var added: String?,
    @ColumnInfo(name = "modified") var modified: String?,
    @ColumnInfo(name = "updated") var updated: String?,
    @ColumnInfo(name = "rating") var rating: Float?,
    @ColumnInfo(name = "iconUrl") var iconUrl: String?,
    @ColumnInfo(name = "graphicUrl") var graphicUrl: String?,
    @ColumnInfo(name = "upType") var upType: String?
)