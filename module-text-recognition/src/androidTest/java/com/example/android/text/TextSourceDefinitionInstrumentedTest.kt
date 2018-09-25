package com.example.android.text

import android.support.test.rule.ActivityTestRule
import com.example.android.analyzer.testutils.SingleFragmentActivity
import com.example.android.analyzer.text.ui.TextSourceDefinitionFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test

//@RunWith(AndroidJUnit4::class)
class TextSourceDefinitionInstrumentedTest {

	val testActivityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, true)

	@Rule
	fun rule() = testActivityRule

	@Before
	fun setUp() {
		rule().activity.setFragment(TextSourceDefinitionFragment())
	}

	@Test
	fun wrongEmailFormat_shouldDisableLoginButton() {

//		login {
//			email("wrong.email.com")
//			asssertLoginButtonDisabled()
//		}

	}
}