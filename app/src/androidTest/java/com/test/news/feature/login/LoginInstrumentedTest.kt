package com.test.news.feature.login

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.test.news.R
import com.test.news.features.login.presentation.LoginActivity
import com.test.news.utils.CommonSteps.loginWithCredentials
import junit.framework.Assert.assertTrue
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class LoginInstrumentedTest {

    @get:Rule
    var activityTestRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun checkLoginScreen() {
        onView(withId(R.id.editTextUserName))
            .check(matches(isDisplayed()))
        onView(withId(R.id.editTextPassword))
            .check(matches(isDisplayed()))
        onView(withId(R.id.buttonLogin))
            .check(matches(isDisplayed()))
    }

    @Test
    fun loginFailedWithInvalidUsername() {
        loginWithCredentials(INVALID_USER_NAME, INVALID_USER_PASSWORD)
        onView(withId(R.id.editTextUserName))
            .check(
                matches(
                    hasErrorText(
                        activityTestRule.activity.getString(R.string.login_wrong_user_name_error)
                    )
                )
            )
    }

    @Test
    fun loginFailedWithInvalidPassword() {
        loginWithCredentials(VALID_USER_NAME, INVALID_USER_PASSWORD)
        onView(withId(R.id.editTextPassword))
            .check(
                matches(
                    hasErrorText(
                        activityTestRule.activity.getString(R.string.login_wrong_password_error)
                    )
                )
            )
    }

    @Test
    fun loginFailedWithInvalidUsernameAndPassword() {
        loginWithCredentials(INVALID_USER_NAME, INVALID_USER_PASSWORD)
        onView(withId(R.id.editTextUserName))
            .check(
                matches(
                    hasErrorText(
                        activityTestRule.activity.getString(R.string.login_wrong_user_name_error)
                    )
                )
            )
        onView(withId(R.id.editTextPassword))
            .check(
                matches(
                    hasErrorText(
                        activityTestRule.activity.getString(R.string.login_wrong_password_error)
                    )
                )
            )
    }

    @Test
    fun shouldLoginWithValidCredentials() {
        loginWithCredentials(VALID_USER_NAME, VALID_USER_PASSWORD)
        assertTrue(activityTestRule.activity.isFinishing)
        onView(withText(R.string.app_name)).check(matches(isDisplayed()))
    }

    companion object {
        private const val VALID_USER_NAME = "user1"
        private const val INVALID_USER_NAME = "1"
        private const val VALID_USER_PASSWORD = "password"
        private const val INVALID_USER_PASSWORD = "Password"
    }
}