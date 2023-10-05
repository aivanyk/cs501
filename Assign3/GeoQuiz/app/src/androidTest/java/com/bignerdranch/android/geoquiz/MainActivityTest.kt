package com.bignerdranch.android.geoquiz

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.ActivityAction
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Root
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>
    private var decorView: View? = null

    @Before
    fun setup() {
        scenario = launch(MainActivity::class.java)
        scenario.onActivity(ActivityAction<MainActivity> { activity ->
            decorView = activity.getWindow().getDecorView()
        })
    }

    @After
    fun teardown() {
        scenario.close()
    }

    @Test
    fun showsFirstQuestionOnLaunch() {
        onView(withId(R.id.question_text_view))
            .check(matches(withText(R.string.question_australia)))
    }

    @Test
    fun showsSecondQuestionAfterNextPress() {
        onView(withId(R.id.next_button)).perform(click())
        onView(withId(R.id.question_text_view)).check(matches(withText(R.string.question_oceans)))
    }

    @Test
    fun handlesActivityRecreation() {
        onView(withId(R.id.next_button)).perform(click())
        scenario.recreate()
        onView(withId(R.id.question_text_view)).check(matches(withText(R.string.question_oceans)))
    }

    // Instrumental test for Task 3
    @Test
    fun handleCheatActivityRecreation() {
        onView(withId(R.id.cheat_button)).perform(click())
        onView(withId(R.id.show_answer_button)).perform(click())
        val uiDevice = UiDevice.getInstance(getInstrumentation())
        uiDevice.setOrientationLeft()
        onView(withId(android.R.id.content)).perform(pressBack())
        onView(withId(R.id.true_button)).perform(click())
        onView(withText(R.string.judgment_toast))
            .inRoot(withDecorView(Matchers.not(decorView)))
            .check(matches(isDisplayed()))
    }

    // Instrumental test for Task 4
    @Test
    fun refreshIsCheaterAfterNext() {
        onView(withId(R.id.cheat_button)).perform(click())
        onView(withId(R.id.show_answer_button)).perform(click())
        onView(withId(android.R.id.content)).perform(pressBack())
        onView(withId(R.id.true_button)).perform(click())
        onView(withText(R.string.judgment_toast))
            .inRoot(withDecorView(Matchers.not(decorView)))
            .check(matches(isDisplayed()))
        onView(withId(R.id.next_button)).perform(click())
        onView(withId(R.id.true_button)).perform(click())
        onView(withText(R.string.correct_toast))
            .inRoot(withDecorView(Matchers.not(decorView)))
            .check(matches(isDisplayed()))
    }

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }
}



