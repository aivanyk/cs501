package com.example.cs501_hw3_q6

import android.content.Context
import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FlashcardActivityTest {

    private lateinit var scenario: ActivityScenario<FlashcardActivity>
    private var decorView: View? = null

    @Before
    fun setUp() {
        scenario = launch(FlashcardActivity::class.java)
        scenario.onActivity(ActivityScenario.ActivityAction<FlashcardActivity> { activity ->
            decorView = activity.getWindow().getDecorView()
        })
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    // Instrumental test 1 for Task 6
    @Test
    fun testQuestion() {
        Espresso.onView(withId(R.id.generateQuestions)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.operation))
            .check(ViewAssertions.matches(withText("+")))
    }

    // Instrumental test 2 for Task 6
    @Test
    fun handleRestart() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        Espresso.onView(withId(R.id.generateQuestions)).perform(ViewActions.click())
        val record1 = context.getString(R.id.topOperand)

        scenario.recreate()
        val record2 = context.getString(R.id.topOperand)
        assertEquals(record1, record2)
    }
}