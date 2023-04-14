package pt.brunoponte.aptoidestore.domain.useCases

import pt.brunoponte.aptoidestore.domain.Page
import pt.brunoponte.aptoidestore.domain.repositories.IAppRepository
import javax.inject.Inject

class GetAppListUseCase
@Inject
constructor(
    private val appRepository: IAppRepository
) {
    suspend fun execute() = appRepository.getApps()
}