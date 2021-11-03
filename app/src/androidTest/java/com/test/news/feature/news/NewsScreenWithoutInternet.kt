package com.test.news.feature.news

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import com.test.news.R
import com.test.news.features.news.presentation.NewsActivity
import org.junit.After
import org.junit.Before
import org.junit.Test

class NewsScreenWithoutInternet {
    @Before
    fun setUp() {
        InstrumentationRegistry.getInstrumentation().uiAutomation
            .executeShellCommand("svc wifi disable")
        InstrumentationRegistry.getInstrumentation().uiAutomation
            .executeShellCommand("svc data disable")
    }

    @After
    fun tearDown() {
        InstrumentationRegistry.getInstrumentation().uiAutomation
            .executeShellCommand("svc wifi enable")
        InstrumentationRegistry.getInstrumentation().uiAutomation
            .executeShellCommand("svc data enable")
    }

    @Test
    fun launchAuthorizedAppSecondTime() {
        var scenario = ActivityScenario.launch(NewsActivity::class.java)
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.textViewError))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


        //  TODO check Retry button after implementation of this button
//        onView(withId(R.id.retryButton)).check(matches(isDisplayed()))
    }
}