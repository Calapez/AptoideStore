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
import pt.brunoponte.aptoidestore.domain.useCases.GetAppUseCase
import pt.brunoponte.aptoidestore.presentation.appDetails.AppDetailsUiModel
import pt.brunoponte.aptoidestore.presentation.appDetails.AppDetailsViewModel
import pt.brunoponte.aptoidestore.presentation.appDetails.AppDetailsViewState
import java.time.LocalDateTime

@RunWith(JUnit4::class)
class AppDetailsViewModelTest {

    lateinit var viewModel: AppDetailsViewModel

    @Mock
    lateinit var getAppUseCase: GetAppUseCase

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    val testDispatcher = UnconfinedTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = AppDetailsViewModel(getAppUseCase, testDispatcher)
    }

    @Test
    fun `get app returns success`() {
        val getAppResult = App(
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
            Mockito.`when`(getAppUseCase.execute(getAppResult.id))
                .thenReturn(Response.Success(getAppResult))

            viewModel.getAppFromId(getAppResult.id)

            assertEquals(
                AppDetailsViewState(
                    isLoading = false,
                    message = null,
                    app = AppDetailsUiModel(
                        getAppResult.id,
                        getAppResult.name,
                        getAppResult.size,
                        getAppResult.downloads,
                        getAppResult.updated,
                        getAppResult.rating,
                        getAppResult.graphicUrl)),
                viewModel.viewState.value
            )
        }
    }

    @Test
    fun `get app returns error`() {
        val getAppErrorException = Exception("Some message")

        runBlocking {
            Mockito.`when`(getAppUseCase.execute(1))
                .thenReturn(Response.Error(getAppErrorException))

            viewModel.getAppFromId(1)

            assertEquals(
                AppDetailsViewState(
                    isLoading = false,
                    message = "Some message",
                    app = null),
                viewModel.viewState.value
            )
        }
    }
}