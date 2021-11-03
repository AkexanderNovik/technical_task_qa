package com.test.news.feature.login

import androidx.core.content.ContextCompat
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.test.news.features.login.presentation.LoginActivity
import com.test.news.features.news.presentation.NewsActivity
import org.junit.Test

class LaunchAuthorizedAppSecondTime {
    @Test
    fun launchAuthorizedAppSecondTime() {
        val scenario = ActivityScenario.launch(LoginActivity::class.java)
        scenario.onActivity { activity ->
            ContextCompat.startActivity(
                activity,
                NewsActivity.getStartIntent(activity.baseContext, true),
                null
            )
        }
        Espresso.onView(ViewMatchers.withText("News Prenium"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}