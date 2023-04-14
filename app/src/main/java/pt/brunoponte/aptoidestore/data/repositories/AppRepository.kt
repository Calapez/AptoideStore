package pt.brunoponte.aptoidestore.data.repositories

import android.util.Log
import pt.brunoponte.aptoidestore.data.dataSources.AppDataSourceFactory
import pt.brunoponte.aptoidestore.domain.Response
import pt.brunoponte.aptoidestore.domain.models.App
import pt.brunoponte.aptoidestore.domain.repositories.IAppRepository
import javax.inject.Inject

class AppRepository
@Inject
constructor(
    private val dataSourceFactory: AppDataSourceFactory
) : IAppRepository {
    override suspend fun getApps(): Response<List<App>> =
        try {
            val apps = dataSourceFactory.getDataSource().getApps()
            Response.Success(apps)
        } catch (exception: Exception) {
            // There was an issue
            exception.printStackTrace()
            Log.e("NetworkLayer", exception.message, exception)
            Response.Error(exception)
        }

    override suspend fun getApp(id: Long): Response<App?> {
        TODO("Not yet implemented")
    }
}