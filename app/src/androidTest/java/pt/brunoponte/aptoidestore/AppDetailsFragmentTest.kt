package pt.brunoponte.aptoidestore

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.os.bundleOf
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.Assert.*
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pt.brunoponte.aptoidestore.helpers.launchFragmentInHiltContainer
import pt.brunoponte.aptoidestore.presentation.appDetails.AppDetailsFragment
import pt.brunoponte.aptoidestore.presentation.appDetails.AppDetailsUiModel

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class AppDetailsFragmentTest {

    private lateinit var fragment: AppDetailsFragment

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testAppLoaded() {
        val fragmentArgs = bundleOf("appId" to 1L)
        launchFragmentInHiltContainer<AppDetailsFragment>(fragmentArgs, R.style.Theme_AptoideStore) {
            fragment = (this as AppDetailsFragment)
        }

        val viewState = fragment.viewModel.viewState.value

        assertNotNull(viewState.app)

        val appUiModel = fragment.viewModel.viewState.value.app as AppDetailsUiModel

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(doesNotExist())
        onView(withId(R.id.loadingProgressBar)).check(matches(not(isDisplayed())))

        onView(withId(R.id.contentView)).check(matches(isDisplayed()))
        onView(withId(R.id.appNameText)).check(matches(withText(
            appUiModel.name)))
        onView(withId(R.id.ratingTextView)).check(matches(withText(
            appUiModel.rating.toString())))
        onView(withId(R.id.downloadsTextView)).check(matches(withText(
            appUiModel.getDownloadsUiString())))
        onView(withId(R.id.sizeTextView)).check(matches(withText(
            appUiModel.getSizeUiString())))
        onView(withId(R.id.lastUpdateTextView)).check(matches(withText(
            appUiModel.getUpdatedDateUiString())))
    }

    @Test
    fun testError() {
        val fragmentArgs = bundleOf("appId" to -999) // This app doesn't exist
        launchFragmentInHiltContainer<AppDetailsFragment>(fragmentArgs, R.style.Theme_AptoideStore) {
            fragment = (this as AppDetailsFragment)
        }

        val viewState = fragment.viewModel.viewState.value

        assertNotNull(viewState.message)

        val errorMsg = fragment.viewModel.viewState.value.message as String

        onView(withId(R.id.loadingProgressBar)).check(matches(not(isDisplayed())))
        onView(withId(R.id.contentView)).check(matches(not(isDisplayed())))

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(isDisplayed()))
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(errorMsg)))

    }

 
}

