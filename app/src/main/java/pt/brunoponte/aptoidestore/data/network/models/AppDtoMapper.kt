package pt.brunoponte.aptoidestore.data.network.models

import pt.brunoponte.aptoidestore.domain.models.App
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AppDtoMapper {

    companion object {

        fun mapToDomainModel(dto: AppDto) = App (
            id = dto.id,
            name = dto.name,
            appPackage = dto.appPackage,
            storeId = dto.storeId,
            storeName = dto.storeName,
            versionName = dto.versionName,
            versionCode = dto.versionCode,
            md5sum = dto.md5sum,
            apkTags = dto.apkTags,
            size = dto.size,
            downloads = dto.downloads,
            pDownloads = dto.pDownloads,
            added = dateFromString(dto.added),
            modified = dateFromString(dto.added),
            updated = dateFromString(dto.added),
            rating = dto.rating,
            iconUrl = dto.iconUrl,
            graphicUrl = dto.graphicUrl,
            upType = dto.upType
        )

        fun toDomainModelList(dtoList: List<AppDto>) = dtoList.map { dto ->
            mapToDomainModel(dto)
        }

        private fun dateFromString(dateString: String?) =
            LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

    }

}