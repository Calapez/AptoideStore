package pt.brunoponte.aptoidestore.data.network.dtos

import pt.brunoponte.aptoidestore.domain.models.App
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NetworkAppMapper {

    companion object {

        fun List<NetworkApp>.asDomainModelList() = map { it.asDomainModel() }

        private fun NetworkApp.asDomainModel() = App (
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

        private fun dateFromString(dateString: String?) =
            LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

    }

}