package pt.brunoponte.aptoidestore.data.cache

import pt.brunoponte.aptoidestore.domain.models.App

interface IAppCache {

    suspend fun getApps(): List<App>
    suspend fun getApp(appId: Long): App?
    suspend fun saveApps(apps: List<App>)
    suspend fun areAppsCached(): Boolean
    suspend fun setLastCacheTime(lastCache: Long)
    suspend fun isExpired(): Boolean

}