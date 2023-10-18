package com.example.task5

import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.FragmentScenario.Companion.launch
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.not
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HintFragmentTest {

    private lateinit var scenario: FragmentScenario<HintFragment>
    @Before
    fun setUp() {
        scenario = launchFragmentInContainer()
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun testHintText(){
        Espresso.onView(withId(R.id.hint)).perform(ViewActions.click())

        Espresso.onView(withId(R.id.hint))
            .check(ViewAssertions.matches(ViewMatchers.withText(not(" HINT: "))))
    }

    @Test
    fun testEnable(){
        Espresso.onView(withId(R.id.hint)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.hint)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.hint)).perform(ViewActions.click())

        Espresso.onView(withId(R.id.hint))
            .check(ViewAssertions.matches(not(ViewMatchers.isEnabled())))
    }
}