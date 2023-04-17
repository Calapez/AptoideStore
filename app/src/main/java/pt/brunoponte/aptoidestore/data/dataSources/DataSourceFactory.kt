package pt.brunoponte.aptoidestore.data.dataSources

import javax.inject.Inject

/**
 * Class used to abstract [RemoteDataSource] and [LocalDataSource]
 */
class DataSourceFactory
@Inject
constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
){
    /**
     * Method responsible for choosing which data source to use
     */
    fun getDataSource(isCached: Boolean) : IDataSource {
        return if (isCached && !localDataSource.isCacheExpired()) {
            localDataSource
        } else {
            remoteDataSource
        }
    }

    fun getCacheDataSource() : IDataSource {
        return localDataSource
    }

}