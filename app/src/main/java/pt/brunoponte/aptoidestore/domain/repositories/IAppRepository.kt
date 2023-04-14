package pt.brunoponte.aptoidestore.domain.repositories

import pt.brunoponte.aptoidestore.domain.Page
import pt.brunoponte.aptoidestore.domain.Response
import pt.brunoponte.aptoidestore.domain.models.App

interface IAppRepository {
    suspend fun getApps() : Response<List<App>>

    suspend fun getApp(id: Long) : Response<App?>
}