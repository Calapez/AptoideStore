package pt.brunoponte.aptoidestore.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pt.brunoponte.aptoidestore.data.repositories.AppRepository
import pt.brunoponte.aptoidestore.domain.repositories.IAppRepository
import javax.inject.Singleton

/**
 * The binding for IAppRepository is on its own module so that we can replace it easily in tests.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAppRepository(
        appRepository: AppRepository,
    ) : IAppRepository {
        return appRepository
    }
}