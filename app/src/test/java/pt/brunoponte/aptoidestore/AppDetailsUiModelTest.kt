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
import pt.brunoponte.aptoidestore.presentation.frontstore.AppItemUiModel
import pt.brunoponte.aptoidestore.presentation.frontstore.FrontstoreViewState
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RunWith(JUnit4::class)
class AppDetailsUiModelTest {

    @Test
    fun `get updated date ui string`() {
        val app = AppDetailsUiModel(
            1,
            null,
            null,
            null,
            LocalDateTime.parse("2023-04-16 10:04:55", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            null,
            null
        )

        assertEquals(
            "16/04/2023 10:04:55",
            app.getUpdatedDateUiString()
        )
    }

    @Test
    fun `get size ui string`() {
        val app = AppDetailsUiModel(
            1,
            null,
            1500000,
            null,
            null,
            null,
            null
        )

        assertEquals(
            "1.5 MB",
            app.getSizeUiString()
        )
    }

    @Test
    fun `get downloads ui string`() {
        val app = AppDetailsUiModel(
            1,
            null,
            null,
            1000000,
            null,
            null,
            null
        )

        assertEquals(
            "1,000,000",
            app.getDownloadsUiString()
        )
    }
}