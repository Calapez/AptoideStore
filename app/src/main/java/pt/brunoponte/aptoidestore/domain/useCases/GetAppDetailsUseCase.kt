package pt.brunoponte.aptoidestore.domain.useCases

import pt.brunoponte.aptoidestore.domain.repositories.IAppRepository

class GetAppDetailsUseCase(
    private val appRepository: IAppRepository
) {
    suspend fun execute(appId: Long) = appRepository.getApp(appId)
}