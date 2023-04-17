package pt.brunoponte.aptoidestore.domain.useCases

import pt.brunoponte.aptoidestore.domain.Response
import pt.brunoponte.aptoidestore.domain.models.App
import pt.brunoponte.aptoidestore.domain.repositories.IAppRepository
import javax.inject.Inject

/**
 * Use Case class used to abstract Data layer from Presentation layer.
 */
class GetAppUseCase
@Inject
constructor(
    private val appRepository: IAppRepository
) {
    suspend fun execute(appId: Long): Response<App> {
        return appRepository.getApp(appId)
    }
}