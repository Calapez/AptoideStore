package pt.brunoponte.aptoidestore.data.network.dtos

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
    @SerializedName("all") var all: AllDto,
)

data class AllDto (
    @SerializedName("data") var data: DataDto,
)

data class DataDto (
    @SerializedName("list") var list: List<NetworkApp>,
)