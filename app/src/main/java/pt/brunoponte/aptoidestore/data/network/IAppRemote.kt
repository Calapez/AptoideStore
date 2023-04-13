package pt.brunoponte.aptoidestore.data.network

import pt.brunoponte.aptoidestore.data.network.models.AppDto

interface IAppRemote {

    suspend fun getApps(): List<AppDto>

}