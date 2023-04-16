package pt.brunoponte.aptoidestore.data.cache.models

import pt.brunoponte.aptoidestore.domain.models.App
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AppEntityMapper {

    companion object {

        fun mapToDomainModel(entity: AppEntity) = App (
            id = entity.id,
            name = entity.name,
            appPackage = entity.appPackage,
            storeId = entity.storeId,
            storeName = entity.storeName,
            versionName = entity.versionName,
            versionCode = entity.versionCode,
            md5sum = entity.md5sum,
            size = entity.size,
            downloads = entity.downloads,
            pDownloads = entity.pDownloads,
            added = dateFromString(entity.added),
            modified = dateFromString(entity.added),
            updated = dateFromString(entity.added),
            rating = entity.rating,
            iconUrl = entity.iconUrl,
            graphicUrl = entity.graphicUrl,
            upType = entity.upType
        )

        fun mapToEntity(model: App) = AppEntity (
            id = model.id,
            name = model.name,
            appPackage = model.appPackage,
            storeId = model.storeId,
            storeName = model.storeName,
            versionName = model.versionName,
            versionCode = model.versionCode,
            md5sum = model.md5sum,
            size = model.size,
            downloads = model.downloads,
            pDownloads = model.pDownloads,
            added = stringFromDate(model.added),
            modified = stringFromDate(model.added),
            updated = stringFromDate(model.added),
            rating = model.rating,
            iconUrl = model.iconUrl,
            graphicUrl = model.graphicUrl,
            upType = model.upType
        )

        fun toEntityList(domainModelList: List<App>) = domainModelList.map { domainModel ->
            mapToEntity(domainModel)
        }

        fun toDomainModelList(entityList: List<AppEntity>) = entityList.map { entity ->
            mapToDomainModel(entity)
        }

        private fun dateFromString(dateString: String?) =
            LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

        private fun stringFromDate(date: LocalDateTime?) =
            date?.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

    }

}