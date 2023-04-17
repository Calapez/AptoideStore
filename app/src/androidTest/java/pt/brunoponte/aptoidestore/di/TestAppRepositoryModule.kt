package pt.brunoponte.aptoidestore.di

import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import pt.brunoponte.aptoidestore.domain.repositories.IAppRepository
import pt.brunoponte.aptoidestore.repository.FakeAppRepository
import javax.inject.Singleton

/**
 * IAppRepository binding to use in tests.
 *
 * Hilt will inject a [FakeAppRepository] instead of a [AppRepository].
 */
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
abstract class TestAppRepositoryModule {
    @Singleton
    @Binds
    abstract fun bindRepository(repo: FakeAppRepository): IAppRepository
}
