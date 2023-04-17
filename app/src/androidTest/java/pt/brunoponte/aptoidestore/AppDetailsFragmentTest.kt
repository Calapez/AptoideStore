package pt.brunoponte.aptoidestore

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.os.bundleOf
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pt.brunoponte.aptoidestore.helpers.launchFragmentInHiltContainer
import pt.brunoponte.aptoidestore.presentation.appDetails.AppDetailsFragment
import pt.brunoponte.aptoidestore.presentation.appDetails.AppDetailsViewState

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class AppDetailsFragmentTest {

    lateinit var fragment: AppDetailsFragment

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testAppDetails() {
        val fragmentArgs = bundleOf("appId" to 1L)
        launchFragmentInHiltContainer<AppDetailsFragment>(fragmentArgs, R.style.Theme_AptoideStore) {
            fragment = (this as AppDetailsFragment)
        }

        assert(fragment.viewModel.viewState.value!!::class == AppDetailsViewState.Content::class)

        val appUiModel = (fragment.viewModel.viewState.value as AppDetailsViewState.Content).app

        onView(withId(R.id.errorView)).check(matches(not(isDisplayed())))
        onView(withId(R.id.loadingProgressIndicator)).check(matches(not(isDisplayed())))

        onView(withId(R.id.contentView)).check(matches(isDisplayed()))
        onView(withId(R.id.appNameText)).check(matches(ViewMatchers.withText(
            appUiModel.name)))
        onView(withId(R.id.ratingTextView)).check(matches(ViewMatchers.withText(
            appUiModel.rating.toString())))
        onView(withId(R.id.downloadsTextView)).check(matches(ViewMatchers.withText(
            appUiModel.getDownloadsUiString())))
        onView(withId(R.id.sizeTextView)).check(matches(ViewMatchers.withText(
            appUiModel.getSizeUiString())))
        onView(withId(R.id.lastUpdateTextView)).check(matches(ViewMatchers.withText(
            appUiModel.getUpdatedDateUiString())))
    }

    @Test
    fun testError() {
        val fragmentArgs = bundleOf("appId" to -999) // This app doesn't exist
        launchFragmentInHiltContainer<AppDetailsFragment>(fragmentArgs, R.style.Theme_AptoideStore) {
            fragment = (this as AppDetailsFragment)
        }

        // TODO: store viewmodel locally
        assert(fragment.viewModel.viewState.value!!::class == AppDetailsViewState.Error::class)

        val errorMsg = (fragment.viewModel.viewState.value as AppDetailsViewState.Error).errorMsg

        onView(withId(R.id.loadingProgressIndicator)).check(matches(not(isDisplayed())))
        onView(withId(R.id.contentView)).check(matches(not(isDisplayed())))
        onView(withId(R.id.errorView)).check(matches(isDisplayed()))
        onView(withId(R.id.errorText)).check(matches(ViewMatchers.withText(errorMsg)))

    }

 
}

