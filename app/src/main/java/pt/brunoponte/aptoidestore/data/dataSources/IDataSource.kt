package pt.brunoponte.aptoidestore.data.dataSources

import pt.brunoponte.aptoidestore.domain.models.App

interface IDataSource {

    // Remote and Local data sources
    suspend fun getApps(): List<App>

    // Local data source only
    suspend fun getApp(appId: Long): App?

    suspend fun saveApps(apps: List<App>)

    suspend fun areAppsCached(): Boolean

}