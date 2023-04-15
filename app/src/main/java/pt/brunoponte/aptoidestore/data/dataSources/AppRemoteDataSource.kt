package pt.brunoponte.aptoidestore.data.dataSources

import pt.brunoponte.aptoidestore.data.network.IAppRemote
import pt.brunoponte.aptoidestore.data.network.models.AppDtoMapper
import pt.brunoponte.aptoidestore.domain.models.App
import javax.inject.Inject

class AppRemoteDataSource
@Inject
constructor(
    private val appRemote: IAppRemote
): IAppDataSource {

    override suspend fun getApps(): List<App> {
        return AppDtoMapper.toDomainModelList(appRemote.getApps())
    }

    override suspend fun getApp(appId: Long): App? {
        throw UnsupportedOperationException("Get App is not supported for RemoteDataSource.")
    }

    override suspend fun saveApps(apps: List<App>) {
        throw UnsupportedOperationException("Save Apps is not supported for RemoteDataSource.")
    }

    override suspend fun areAppsCached(): Boolean {
        throw UnsupportedOperationException("Cache is not supported for RemoteDataSource.")
    }

}