package pt.brunoponte.aptoidestore.data.dataSources

import pt.brunoponte.aptoidestore.data.network.AppRemote
import pt.brunoponte.aptoidestore.data.network.IAppRemote
import pt.brunoponte.aptoidestore.data.network.models.AppDtoMapper
import pt.brunoponte.aptoidestore.domain.models.App
import javax.inject.Inject

class AppRemoteDataSource
@Inject
constructor(
    private val appRemote: AppRemote
): IAppDataSource {

    override suspend fun getApps(): List<App> {
        return AppDtoMapper.toDomainModelList(appRemote.getApps())
    }

}