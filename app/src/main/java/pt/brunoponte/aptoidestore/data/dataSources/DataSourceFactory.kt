package pt.brunoponte.aptoidestore.data.dataSources

import javax.inject.Inject

class DataSourceFactory
@Inject
constructor(
    private val cacheDataSource: CacheDataSource,
    private val remoteDataSource: RemoteDataSource
){

    suspend fun getDataSource(isCached: Boolean) : IDataSource {
        return if (isCached && !cacheDataSource.isExpired()) {
            cacheDataSource
        } else {
            remoteDataSource
        }
    }

    fun getCacheDataSource() : IDataSource {
        return cacheDataSource
    }

}