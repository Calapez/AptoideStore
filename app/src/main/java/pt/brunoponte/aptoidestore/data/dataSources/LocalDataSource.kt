package pt.brunoponte.aptoidestore.data.dataSources

import pt.brunoponte.aptoidestore.data.cache.daos.AppDao
import pt.brunoponte.aptoidestore.data.cache.models.AppEntityMapper
import pt.brunoponte.aptoidestore.data.cache.utils.CachePreferencesHelper
import pt.brunoponte.aptoidestore.domain.models.App
import javax.inject.Inject

class LocalDataSource
@Inject
constructor(
    private val appDao: AppDao,
    private val preferencesHelper: CachePreferencesHelper
): IDataSource {
    override suspend fun getApps(): List<App> {
        val appEntities = appDao.getApps()
        return AppEntityMapper.toDomainModelList(appEntities)
    }

    override suspend fun getApp(appId: Long): App? {
        return appDao.getApp(appId)?.let { AppEntityMapper.mapToDomainModel(it) }
    }

    override suspend fun saveApps(apps: List<App>) {
        appDao.insertApps(AppEntityMapper.toEntityList(apps))
        setLastCacheUpdateTime(System.currentTimeMillis())
    }

    override suspend fun areAppsCached(): Boolean {
        return getApps().isNotEmpty()
    }

    fun isCacheExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = getLastCacheUpdateTime()
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

    /**
     * Set in millis, the last time the cache was updated.
     */
    private fun setLastCacheUpdateTime(lastCache: Long) {
        preferencesHelper.lastCacheTime = lastCache
    }

    /**
     * Get in millis, the last time the cache was updated.
     */
    private fun getLastCacheUpdateTime(): Long {
        return preferencesHelper.lastCacheTime
    }

    companion object {
        /**
         * Expiration time set to 5 minutes
         */
        const val EXPIRATION_TIME = (60 * 5 * 1000).toLong()
    }

}