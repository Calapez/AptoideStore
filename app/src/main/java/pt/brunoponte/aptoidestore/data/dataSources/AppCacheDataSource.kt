package pt.brunoponte.aptoidestore.data.dataSources

import pt.brunoponte.aptoidestore.data.cache.IAppCache
import pt.brunoponte.aptoidestore.domain.models.App
import javax.inject.Inject

class AppCacheDataSource
@Inject
constructor(
    private val appCache: IAppCache
): IAppDataSource {

    override suspend fun getApps(): List<App> {
        return appCache.getApps()
    }

    override suspend fun getApp(appId: Long): App? {
        return appCache.getApp(appId)
    }

    override suspend fun saveApps(apps: List<App>) {
        appCache.saveApps(apps)
        appCache.setLastCacheTime(System.currentTimeMillis())
    }

    override suspend fun areAppsCached(): Boolean {
       return appCache.areAppsCached()
    }

}