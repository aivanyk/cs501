package com.example.hw3question5

import android.view.View
import android.widget.TextView
import org.junit.Assert.*

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.runner.RunWith
import java.util.regex.Matcher


class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun showInitValueOnLaunch(){
        onView(withId(R.id.celsius)).check(matches(withText("0")))
        onView(withId(R.id.fahrenheit)).check(matches(withText("32")))
    }

    // check if C&F change together when clicking on either seekbar,
    // imported getText() from outside to get text from a TextView
    @Test
    fun valueChangeTogether(){
        onView(withId(R.id.celsiusSeekBar)).perform(click())
        var celsiusText = (getText(onView(withId(R.id.fahrenheit))).toInt()-32)*5/9
        onView(withId(R.id.celsius)).check(matches(withText(celsiusText.toString())))

        onView(withId(R.id.fahrenheitSeekBar)).perform(click())
        var fahrenheitText = getText(onView(withId(R.id.celsius))).toInt()*9/5+32
        onView(withId(R.id.fahrenheit)).check(matches(withText(fahrenheitText.toString())))
    }


    // from website https://stackoverflow.com/questions/45597008/espresso-get-text-of-element
    private fun getText(matcher: ViewInteraction): String {
        var text = String()
        matcher.perform(object : ViewAction {

            override fun getDescription(): String {
                return "Text of the view"
            }

            override fun getConstraints(): org.hamcrest.Matcher<View> {
                return isAssignableFrom(TextView::class.java)
            }

            override fun perform(uiController: UiController, view: View) {
                val tv = view as TextView
                text = tv.text.toString()
            }
        })

        return text
    }
}