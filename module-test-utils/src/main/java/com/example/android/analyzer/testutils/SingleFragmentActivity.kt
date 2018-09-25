package com.example.android.analyzer.testutils

import android.os.Bundle
import android.support.annotation.RestrictTo
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.android.analyzer.test.R

/**
 * Used as container to test fragments in isolation with Espresso
 */
@RestrictTo(RestrictTo.Scope.TESTS)
class SingleFragmentActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val content = FrameLayout(this)
		content.layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
			ViewGroup.LayoutParams.MATCH_PARENT)
		content.id = R.id.content_frame
		setContentView(content)
	}

	fun setFragment(fragment: Fragment) {
		supportFragmentManager.beginTransaction()
			.add(R.id.content_frame, fragment, "TEST")
			.commit()
	}

	fun replaceFragment(fragment: Fragment) {
		supportFragmentManager.beginTransaction()
			.replace(R.id.content_frame, fragment).commit()
	}
}