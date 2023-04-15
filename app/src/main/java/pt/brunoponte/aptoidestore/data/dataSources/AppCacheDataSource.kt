package pt.brunoponte.aptoidestore.data.dataSources

import pt.brunoponte.aptoidestore.domain.models.App

class AppCacheDataSource : IAppDataSource {

    override suspend fun getApps(): List<App> {
        TODO("Not yet implemented")
    }

}