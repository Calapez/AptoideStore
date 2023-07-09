package pt.brunoponte.aptoidestore.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import pt.brunoponte.aptoidestore.data.local.daos.AppDao
import pt.brunoponte.aptoidestore.data.local.entities.AppEntity

@Database(
    entities = [AppEntity::class],
    version = 1)
abstract class AptoideStoreDatabase: RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object{
        const val DATABASE_NAME = "aptoide_store_db"
    }

}