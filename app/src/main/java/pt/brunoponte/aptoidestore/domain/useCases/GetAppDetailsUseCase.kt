package pt.brunoponte.aptoidestore.domain.useCases

import pt.brunoponte.aptoidestore.domain.repositories.IAppRepository
import javax.inject.Inject

class GetAppDetailsUseCase
@Inject
constructor(
    private val appRepository: IAppRepository
) {
    suspend fun execute(appId: Long) = appRepository.getApp(appId)
}