package pt.brunoponte.aptoidestore

import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pt.brunoponte.aptoidestore.helpers.RecyclerViewItemCountAssertion.Companion.withItemCount
import pt.brunoponte.aptoidestore.helpers.launchFragmentInHiltContainer
import pt.brunoponte.aptoidestore.presentation.frontstore.FrontstoreFragment
import pt.brunoponte.aptoidestore.presentation.frontstore.editorsAppListAdapter.EditorsAppListItemViewHolder
import pt.brunoponte.aptoidestore.presentation.frontstore.topAppListAdapter.TopAppListItemViewHolder


@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class FrontstoreFragmentTest {

    private lateinit var fragment: FrontstoreFragment

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testLoadedApps() {
        launchFragmentInHiltContainer<FrontstoreFragment>(Bundle(), R.style.Theme_AptoideStore) {
            fragment = (this as FrontstoreFragment)
        }

        onView(withId(R.id.editorsRecyclerView)).check(withItemCount(3))
        onView(withId(R.id.topRecyclerView)).check(withItemCount(3))
    }

    @Test
    fun testClickEditorsAppNavigateToDetails() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        launchFragmentInHiltContainer<FrontstoreFragment>(Bundle(), R.style.Theme_AptoideStore) {
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(R.id.frontstoreFragment)
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.editorsRecyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<EditorsAppListItemViewHolder>(0, click()))

        assertEquals(navController.currentDestination?.id, R.id.appDetailsFragment)
    }

    @Test
    fun testClickTopAppNavigateToDetails() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        launchFragmentInHiltContainer<FrontstoreFragment>(Bundle(), R.style.Theme_AptoideStore) {
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(R.id.frontstoreFragment)
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.topRecyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<TopAppListItemViewHolder>(0, click()))

        assertEquals(navController.currentDestination?.id, R.id.appDetailsFragment)
    }
}
