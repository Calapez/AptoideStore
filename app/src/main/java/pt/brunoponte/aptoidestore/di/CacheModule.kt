package pt.brunoponte.aptoidestore.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pt.brunoponte.aptoidestore.data.local.daos.AppDao
import pt.brunoponte.aptoidestore.data.local.AptoideStoreDatabase
import pt.brunoponte.aptoidestore.data.local.utils.CachePreferencesHelper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

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
    fun providePreferenceHelper(@ApplicationContext context: Context): CachePreferencesHelper {
        return CachePreferencesHelper(context)
    }

}