package pt.brunoponte.aptoidestore.data.dataSources

import pt.brunoponte.aptoidestore.domain.models.App

interface IDataSource {

    // Remote and Cache
    suspend fun getApps(): List<App>

    // Cache only
    suspend fun getApp(appId: Long): App?

    suspend fun saveApps(apps: List<App>)
    suspend fun areAppsCached(): Boolean

}