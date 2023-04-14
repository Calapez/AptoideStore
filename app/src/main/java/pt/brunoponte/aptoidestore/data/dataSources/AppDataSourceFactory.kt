package pt.brunoponte.aptoidestore.data.dataSources

import javax.inject.Inject

class AppDataSourceFactory
@Inject
constructor(
    private val remoteDataSource: AppRemoteDataSource
){

    fun getDataSource() : IAppDataSource {
        // TOOD: Check if should return cache data source
        return remoteDataSource
    }

}