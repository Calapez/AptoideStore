package pt.brunoponte.aptoidestore

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import pt.brunoponte.aptoidestore.domain.Response
import pt.brunoponte.aptoidestore.domain.models.App
import pt.brunoponte.aptoidestore.domain.repositories.IAppRepository
import pt.brunoponte.aptoidestore.domain.useCases.GetAppUseCase
import java.time.LocalDateTime

@RunWith(JUnit4::class)
class GetAppUseCaseTest {

    private val app = App(
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

    private lateinit var useCase: GetAppUseCase

    @Mock
    lateinit var appRepository: IAppRepository

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        useCase = GetAppUseCase(appRepository)
    }

    @Test
    fun `invoke returns repo response`() {
        runBlocking {
            Mockito.`when`(useCase.execute(1))
                .thenReturn(Response.Success(app))

            val result = useCase.execute(1)

            assertEquals(Response.Success(app), result)
        }
    }
}