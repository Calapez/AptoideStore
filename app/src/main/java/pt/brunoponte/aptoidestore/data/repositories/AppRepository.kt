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
            val isCached = dataSourceFactory.getCacheDataSource().areAppsCached()
            // Get breeds from the proper source
            val apps = dataSourceFactory.getDataSource(isCached).getApps()
            // Save apps
            dataSourceFactory.getCacheDataSource().saveApps(apps)
            Response.Success(apps)
        } catch (exception: Exception) {
            // There was an issue
            exception.printStackTrace()
            Log.e("NetworkLayer", exception.message, exception)
            Response.Error(exception)
        }

    override suspend fun getApp(id: Long): Response<App> =
        try {
            val app = dataSourceFactory.getCacheDataSource().getApp(id)
            if (app == null) {
                Response.Error(Exception("App not found"))
            } else {
                Response.Success(app)
            }
        } catch (exception: Exception) {
            // There was an issue
            exception.printStackTrace()
            Log.e("CacheLayer", exception.message, exception)
            Response.Error(exception)
        }
}