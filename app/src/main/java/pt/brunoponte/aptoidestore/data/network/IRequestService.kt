package pt.brunoponte.aptoidestore.data.network

import pt.brunoponte.aptoidestore.data.network.dtos.GetAppsResponseDto
import retrofit2.http.GET

interface IRequestService {

    @GET("bulkRequest/api_list/listApps")
    suspend fun getApps() : GetAppsResponseDto

}