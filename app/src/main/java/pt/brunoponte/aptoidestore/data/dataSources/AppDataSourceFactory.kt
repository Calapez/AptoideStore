package pt.brunoponte.aptoidestore.data.dataSources

import pt.brunoponte.aptoidestore.data.cache.IAppCache
import javax.inject.Inject

class AppDataSourceFactory
@Inject
constructor(
    private val appCache: IAppCache,
    private val cacheDataSource: AppCacheDataSource,
    private val remoteDataSource: AppRemoteDataSource
){

    suspend fun getDataSource(isCached: Boolean) : IAppDataSource {
        return if (isCached && !appCache.isExpired()) {
            cacheDataSource
        } else {
            remoteDataSource
        }
    }

    fun getCacheDataSource() : IAppDataSource {
        return remoteDataSource
    }

}