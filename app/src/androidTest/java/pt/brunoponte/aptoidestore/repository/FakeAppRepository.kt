package pt.brunoponte.aptoidestore.repository

import pt.brunoponte.aptoidestore.domain.Response
import pt.brunoponte.aptoidestore.domain.models.App
import pt.brunoponte.aptoidestore.domain.repositories.IAppRepository
import java.time.LocalDateTime
import javax.inject.Inject

/**
 * Implementation of Repository with hardcoded data for easy testing.
 */
class FakeAppRepository @Inject constructor() : IAppRepository {

    private val appList = mutableListOf(
        App(
            1,
            "Name1",
            "Package1",
            1,
            "StoreName1",
            "VersionName1",
            1,
            "Md5sum1",
            1,
            1,
            1,
            LocalDateTime.now(),
            LocalDateTime.now(),
            LocalDateTime.now(),
            1f,
            "url1",
            "url1",
            "upType1"
        ),
        App(
            2,
            "Name2",
            "Package2",
            2,
            "StoreName2",
            "VersionName2",
            2,
            "Md5sum2",
            2,
            2,
            2,
            LocalDateTime.now(),
            LocalDateTime.now(),
            LocalDateTime.now(),
            2f,
            "url2",
            "url2",
            "upType2"
        ),
        App(
            3,
            "Name3",
            "Package3",
            3,
            "StoreName3",
            "VersionName3",
            3,
            "Md5sum3",
            3,
            3,
            3,
            LocalDateTime.now(),
            LocalDateTime.now(),
            LocalDateTime.now(),
            3f,
            "url3",
            "url3",
            "upType3"
        )
    )


    override suspend fun getApps() = Response.Success(appList)

    override suspend fun getApp(id: Long): Response<App> {
        val app = appList.firstOrNull { it.id == id }
        return if (app != null)
            Response.Success(app)
        else
            Response.Error(Exception())
    }


}