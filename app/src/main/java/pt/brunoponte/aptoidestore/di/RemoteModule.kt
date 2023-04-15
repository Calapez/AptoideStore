package pt.brunoponte.aptoidestore.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pt.brunoponte.aptoidestore.data.network.AppRemote
import pt.brunoponte.aptoidestore.data.network.IAppRemote
import pt.brunoponte.aptoidestore.data.network.IRequestService
import pt.brunoponte.aptoidestore.data.network.utils.Endpoints
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun provideRequestService() : IRequestService {
        return Retrofit.Builder()
            .baseUrl(Endpoints.aptoideApiEndpoint)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IRequestService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppRemote(appRemote: AppRemote): IAppRemote {
        return appRemote
    }
}