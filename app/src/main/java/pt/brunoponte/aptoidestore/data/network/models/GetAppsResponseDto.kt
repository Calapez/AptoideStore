package pt.brunoponte.aptoidestore.data.network.models

import com.google.gson.annotations.SerializedName

data class GetAppsResponseDto (
    @SerializedName("responses") var responses: ResponsesDto,
)

data class ResponsesDto (
    @SerializedName("listApps") var listApps: ListAppsDto,
)

data class ListAppsDto (
    @SerializedName("datasets") var datasets: DatasetsDto,
)

data class DatasetsDto (
    @SerializedName("data") var data: DataDto,
)

data class DataDto (
    @SerializedName("list") var list: List<AppDto>,
)