package com.example.android.analyzer.ui

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.android.analyzer.testutils.TaskExecutorWithIdlingResourceRule
import com.example.android.analyzer.text.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TextSourceDefinitionInstrumentedTest {

	@Rule
	@JvmField
	var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

	@Rule
	@JvmField
	val executorRule = TaskExecutorWithIdlingResourceRule()

	@Test
	fun choose_a_file() {

		onView(withId(R.id.text_recognition_navigation)).perform(click())
		onView(withId(R.id.appointment_card_content)).perform(click())

	}
}