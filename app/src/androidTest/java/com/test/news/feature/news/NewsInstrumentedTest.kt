package com.test.news.feature.news

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.test.news.R
import com.test.news.features.news.presentation.NewsActivity
import com.test.news.utils.CommonSteps.withIndex
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewsScreenTest {

    @get:Rule
    var activityTestRule = ActivityTestRule(NewsActivity::class.java)

    @Before
    fun setup() {
        Thread.sleep(1000)
    }

    @Test
    fun checkNewsImagesAreLoaded() {
        onView(withIndex(withId(R.id.recyclerViewImageWidget), 1))
            .check(matches(isDisplayed()))
        onView(withIndex(withId(R.id.imageView), 1))
            .check(matches(isDisplayed()))
    }

    @Test
    fun openClickedImageInExternalBrowser() {
        onView(withIndex(withId(R.id.imageView), 1))
            .perform(click())
    }
}