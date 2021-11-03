package com.test.news.utils

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.test.news.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

object CommonSteps {
    fun loginWithCredentials(name: String, password: String) {
        onView(withId(R.id.editTextUserName))
            .perform(clearText(), typeText(name))
        onView(withId(R.id.editTextPassword))
            .perform(clearText(), typeText(password))
        onView(withId(R.id.buttonLogin))
            .perform(click())
    }

    /**Method allows to pick an element by [index]*/
    fun withIndex(matcher: Matcher<View?>, index: Int): Matcher<View?>? {
        return object : TypeSafeMatcher<View?>() {
            var currentIndex = 0
            override fun describeTo(description: Description) {
                description.appendText("with index: ")
                description.appendValue(index)
                matcher.describeTo(description)
            }

            override fun matchesSafely(view: View?): Boolean {
                return matcher.matches(view) && currentIndex++ == index
            }
        }
    }
}