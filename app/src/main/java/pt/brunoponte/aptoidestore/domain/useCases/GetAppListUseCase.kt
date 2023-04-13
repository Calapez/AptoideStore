package pt.brunoponte.aptoidestore.domain.useCases

import pt.brunoponte.aptoidestore.domain.Page
import pt.brunoponte.aptoidestore.domain.repositories.IAppRepository

class GetAppListUseCase(
    private val appRepository: IAppRepository
) {
    suspend fun execute(page: Page) = appRepository.getApps(page)
}