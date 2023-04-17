package pt.brunoponte.aptoidestore.data.dataSources

import pt.brunoponte.aptoidestore.data.network.IRequestService
import pt.brunoponte.aptoidestore.data.network.models.AppDtoMapper
import pt.brunoponte.aptoidestore.domain.models.App
import javax.inject.Inject

class RemoteDataSource
@Inject
constructor(
    private val requestService: IRequestService
): IDataSource {

    override suspend fun getApps(): List<App> {
        return AppDtoMapper.toDomainModelList(requestService.getApps()
            .responses
            .listApps
            .datasets
            .all
            .data
            .list)
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