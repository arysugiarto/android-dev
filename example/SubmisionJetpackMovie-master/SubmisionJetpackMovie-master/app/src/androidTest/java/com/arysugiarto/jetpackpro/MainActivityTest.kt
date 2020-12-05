package com.arysugiarto.jetpackpro

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.arysugiarto.jetpackpro.ui.MainActivity
import com.arysugiarto.jetpackpro.utils.DataDummy
import com.arysugiarto.jetpackpro.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val dummyMovies = DataDummy().generateRemoteDummyMovies()
    private val dummyTvShows = DataDummy().generateRemoteDummyTvShows()

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }



    @Test
    fun testAppBehaviour(){
        onView(withId(R.id.nav_view)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_movie)).check(matches(isDisplayed()))

        onView(withId(R.id.navigation_movie)).perform(click())
        onView(withId(R.id.rv_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        Espresso.pressBack()

        onView(withId(R.id.navigation_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_tv_show)).perform(click())

        onView(withId(R.id.rv_tv_show))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        Espresso.pressBack()

    }

}