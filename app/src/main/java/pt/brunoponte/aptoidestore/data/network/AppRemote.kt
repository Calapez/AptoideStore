package pt.brunoponte.aptoidestore.data.network

// TODO: Check if we even need this abstraction
class AppRemote (
    private val requestService: IRequestService
) : IAppRemote {

    override suspend fun getApps() =
        requestService.getApps()
            .responses
            .listApps
            .datasets
            .data
            .list

}