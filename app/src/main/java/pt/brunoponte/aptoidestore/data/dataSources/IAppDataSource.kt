package pt.brunoponte.aptoidestore.data.dataSources

import pt.brunoponte.aptoidestore.domain.models.App

interface IAppDataSource {

    suspend fun getApps(): List<App>

}