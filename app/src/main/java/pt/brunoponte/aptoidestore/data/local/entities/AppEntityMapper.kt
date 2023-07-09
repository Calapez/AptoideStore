package pt.brunoponte.aptoidestore.data.local.entities

import pt.brunoponte.aptoidestore.domain.models.App
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AppEntityMapper {

    companion object {

        fun AppEntity.asDomainModel() = App (
            id = id,
            name = name,
            appPackage = appPackage,
            storeId = storeId,
            storeName = storeName,
            versionName = versionName,
            versionCode = versionCode,
            md5sum = md5sum,
            size = size,
            downloads = downloads,
            pDownloads = pDownloads,
            added = dateFromString(added),
            modified = dateFromString(added),
            updated = dateFromString(added),
            rating = rating,
            iconUrl = iconUrl,
            graphicUrl = graphicUrl,
            upType = upType
        )

        fun List<App>.asEntityList() = map { it.asEntity() }

        fun List<AppEntity>.asDomainModelList() = map { it.asDomainModel() }

        private fun App.asEntity() = AppEntity (
            id = id,
            name = name,
            appPackage = appPackage,
            storeId = storeId,
            storeName = storeName,
            versionName = versionName,
            versionCode = versionCode,
            md5sum = md5sum,
            size = size,
            downloads = downloads,
            pDownloads = pDownloads,
            added = stringFromDate(added),
            modified = stringFromDate(added),
            updated = stringFromDate(added),
            rating = rating,
            iconUrl = iconUrl,
            graphicUrl = graphicUrl,
            upType = upType
        )

        private fun dateFromString(dateString: String?) =
            LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

        private fun stringFromDate(date: LocalDateTime?) =
            date?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

    }

}