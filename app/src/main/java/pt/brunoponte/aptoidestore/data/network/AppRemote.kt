package pt.brunoponte.aptoidestore.data.network

import javax.inject.Inject

// TODO: Check if we even need this abstraction
class AppRemote
@Inject
constructor(
    private val requestService: IRequestService
) : IAppRemote {

    override suspend fun getApps() =
        requestService.getApps()
            .responses
            .listApps
            .datasets
            .all
            .data
            .list

}