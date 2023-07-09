package pt.brunoponte.aptoidestore.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pt.brunoponte.aptoidestore.data.local.entities.AppEntity

@Dao
interface AppDao {

    @Query("SELECT * FROM apps")
    suspend fun getApps(): List<AppEntity>

    @Query("SELECT * FROM apps WHERE id = :appId")
    suspend fun getApp(appId: Long): AppEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApps(apps: List<AppEntity>): LongArray

    @Query("DELETE FROM apps")
    suspend fun nukeTable()

}