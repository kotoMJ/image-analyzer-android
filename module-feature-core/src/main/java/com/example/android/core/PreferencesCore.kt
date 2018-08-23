package com.example.android.core

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

open class PreferencesCore @Inject constructor(
	val context: Context,
	private val sharedPreferences: SharedPreferences
) {

	companion object {
		private const val PREFS_SAMPLE_TOKEN = "prefs_sample_token"
	}

	open var sampleToken: Long
		get() = sharedPreferences.getLong(PREFS_SAMPLE_TOKEN, -1)
		set(userId) {
			sharedPreferences.edit().putLong(PREFS_SAMPLE_TOKEN, userId).apply()
		}

	open fun clearSampleToken() {
		sharedPreferences.edit().putString(PREFS_SAMPLE_TOKEN, null).apply()
	}

}