package com.example.tiptime

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorTests {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_20_percent_tip(){
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("50.00"))
            .perform(closeSoftKeyboard())

        onView(withId(R.id.option_twenty_percent)).perform(click())
        onView(withId(R.id.calculate_button)).perform(click())

        onView(withId(R.id.tip_result)).check(matches(withText(containsString("10.00"))))
    }


    @Test
    fun calculate_18_percent_tip(){
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("100.00"))
            .perform(closeSoftKeyboard())

        onView(withId(R.id.option_eighteen_percent)).perform(click())

        onView(withId(R.id.calculate_button)).perform(click())

        onView(withId(R.id.tip_result)).check(matches(withText(containsString("18.00"))))
    }

    @Test
    fun calculate_15_percent_tip(){
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("150.00"))
            .perform(closeSoftKeyboard())

        onView(withId(R.id.option_fifteen_percent)).perform(click())
        onView(withId(R.id.calculate_button)).perform(click())

        onView(withId(R.id.tip_result)).check(matches(withText(containsString("23.00"))))
    }

    @Test
    fun round_up_test(){
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("49.00"))
            .perform(closeSoftKeyboard())

        onView(withId(R.id.option_fifteen_percent)).perform(click())
        onView(withId(R.id.calculate_button)).perform(click())

        onView(withId(R.id.tip_result)).check(matches(withText(containsString("8.00"))))

        onView(withId(R.id.round_up_switch)).perform(click())

        onView(withId(R.id.calculate_button)).perform(click())
        onView(withId(R.id.tip_result)).check(matches(withText(containsString("7.35"))))
    }
}