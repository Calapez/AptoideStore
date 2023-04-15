package pt.brunoponte.aptoidestore.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pt.brunoponte.aptoidestore.data.cache.AppCache
import pt.brunoponte.aptoidestore.data.cache.IAppCache
import pt.brunoponte.aptoidestore.data.cache.daos.AppDao
import pt.brunoponte.aptoidestore.data.cache.database.AptoideStoreDatabase
import pt.brunoponte.aptoidestore.data.cache.utils.CachePreferencesHelper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AptoideStoreDatabase {
        return Room
            .databaseBuilder(
                context.applicationContext,
                AptoideStoreDatabase::class.java,
                AptoideStoreDatabase.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideAppDao(db: AptoideStoreDatabase): AppDao {
        return db.appDao()
    }

    @Provides
    @Singleton
    fun provideAppCache(appCache: AppCache): IAppCache {
        return appCache
    }

    @Provides
    @Singleton
    fun providePreferenceHelper(@ApplicationContext context: Context): CachePreferencesHelper {
        return CachePreferencesHelper(context)
    }

}