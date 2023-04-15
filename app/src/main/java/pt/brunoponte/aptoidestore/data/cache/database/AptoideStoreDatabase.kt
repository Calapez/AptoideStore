package pt.brunoponte.aptoidestore.data.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pt.brunoponte.aptoidestore.data.cache.daos.AppDao
import pt.brunoponte.aptoidestore.data.cache.models.AppEntity

@Database(
    entities = [AppEntity::class],
    version = 1)
abstract class AptoideStoreDatabase: RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object{
        const val DATABASE_NAME = "aptoide_store_db"
    }

}