package pt.brunoponte.aptoidestore

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
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
import pt.brunoponte.aptoidestore.domain.useCases.GetAppListUseCase
import pt.brunoponte.aptoidestore.presentation.frontstore.AppItemUiModel
import pt.brunoponte.aptoidestore.presentation.frontstore.FrontstorePresenter
import pt.brunoponte.aptoidestore.presentation.frontstore.FrontstoreViewState
import java.time.LocalDateTime

@RunWith(JUnit4::class)
class FrontstoreViewModelTest {

    private val getAppsResult = mutableListOf(
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

    lateinit var viewModel: FrontstorePresenter

    @Mock
    lateinit var getAppListUseCase: GetAppListUseCase

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    val testDispatcher = UnconfinedTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = FrontstorePresenter(getAppListUseCase, testDispatcher)
    }

    @Test
    fun `get apps`() {
        runBlocking {
            Mockito.`when`(getAppListUseCase.execute())
                .thenReturn(Response.Success(getAppsResult))

            viewModel.getApps()

            assertEquals(
                FrontstoreViewState(
                    isLoading = false,
                    message = null,
                    apps = getAppsResult.map {
                        AppItemUiModel(it.id, it.name, it.rating, it.graphicUrl, it.iconUrl)
                    }),
                viewModel.viewState.value
            )
        }
    }

    @Test
    fun `get apps empty`() {
        runBlocking {
            Mockito.`when`(getAppListUseCase.execute())
                .thenReturn(Response.Success(listOf()))

            viewModel.getApps()

            assertEquals(
                FrontstoreViewState(
                    isLoading = false,
                    message = null,
                    apps = listOf()
                ),
                viewModel.viewState.value)
        }
    }

    @Test
    fun `get apps error`() {
        val getAppsErrorException = Exception("Some message")

        runBlocking {
            Mockito.`when`(getAppListUseCase.execute())
                .thenReturn(Response.Error(getAppsErrorException))

            viewModel.getApps()

            assertEquals(
                FrontstoreViewState(
                    isLoading = false,
                    message = "Some message",
                    apps = null),
                viewModel.viewState.value
            )
        }
    }
}