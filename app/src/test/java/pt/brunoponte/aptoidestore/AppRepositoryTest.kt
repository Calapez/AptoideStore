package pt.brunoponte.aptoidestore

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import pt.brunoponte.aptoidestore.data.dataSources.DataSourceFactory
import pt.brunoponte.aptoidestore.data.dataSources.IDataSource
import pt.brunoponte.aptoidestore.data.repositories.AppRepository
import pt.brunoponte.aptoidestore.domain.Response
import pt.brunoponte.aptoidestore.domain.models.App
import java.time.LocalDateTime

@RunWith(JUnit4::class)
class AppRepositoryTest {

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
        )
    )

    private lateinit var repository: AppRepository

    @Mock
    lateinit var dataSourceFactory: DataSourceFactory

    @Mock
    lateinit var appDataSource: IDataSource

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = AppRepository(dataSourceFactory)
    }

    @Test
    fun `get apps cached`() {
        runBlocking {
            Mockito.`when`(dataSourceFactory.getCacheDataSource()).thenReturn(appDataSource)
            Mockito.`when`(appDataSource.areAppsCached()).thenReturn(true)

            Mockito.`when`(dataSourceFactory.getDataSource(true)).thenReturn(appDataSource)
            Mockito.`when`(appDataSource.getApps()).thenReturn(appList)

            val getAppsResult = repository.getApps()

            assertEquals(Response.Success(appList), getAppsResult)
        }
    }

    @Test
    fun `get apps no cache`() {
        runBlocking {
            Mockito.`when`(dataSourceFactory.getCacheDataSource()).thenReturn(appDataSource)
            Mockito.`when`(appDataSource.areAppsCached()).thenReturn(false)

            Mockito.`when`(dataSourceFactory.getDataSource(false)).thenReturn(appDataSource)
            Mockito.`when`(appDataSource.getApps()).thenReturn(appList)

            val getAppsResult = repository.getApps()

            assertEquals(Response.Success(appList), getAppsResult)
        }
    }

    @Test(expected = Exception::class)
    fun `get apps error`() {
        val thrownException = Exception("some message")

        runBlocking {
            Mockito.`when`(dataSourceFactory.getCacheDataSource().areAppsCached()).thenThrow(thrownException)

            val getAppsResult = repository.getApps()

            assertEquals(Response.Error(thrownException), getAppsResult)
        }
    }

    @Test
    fun `get app`() {
        val returnedApp = App(
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
        )

        runBlocking {
            Mockito.`when`(dataSourceFactory.getCacheDataSource()).thenReturn(appDataSource)
            Mockito.`when`(appDataSource.getApp(returnedApp.id)).thenReturn(returnedApp)

            val getAppResult = repository.getApp(returnedApp.id)

            assertEquals(Response.Success(returnedApp), getAppResult)
        }
    }

    @Test
    fun `get app not found`() {
        runBlocking {
            Mockito.`when`(dataSourceFactory.getCacheDataSource()).thenReturn(appDataSource)
            Mockito.`when`(appDataSource.getApp(1)).thenReturn(null)

            val getAppResult = repository.getApp(1)

            assert(Response.Error::class == getAppResult::class)
        }
    }

    @Test(expected = Exception::class)
    fun `get app error`() {
        val thrownException = Exception("some message")

        runBlocking {
            Mockito.`when`(dataSourceFactory.getCacheDataSource()).thenThrow(thrownException)

            val getAppResult = repository.getApp(1)

            assertEquals(Response.Error(thrownException), getAppResult)
        }
    }

}