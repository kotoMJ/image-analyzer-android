package com.example.android.coverage.ui.settings

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.android.core.PreferencesCore
import javax.inject.Inject

class SettingsViewModel() : ViewModel() {

	@Inject
	lateinit var preferencesCore: PreferencesCore

	val token: MutableLiveData<String> = MutableLiveData()
	val dummy: String = "mydummy"

	init {
		token.value = "testicek"
	}

//	val token : String = "ddd"

//	fun getToken(): LiveData<String> {
//		val ret = MutableLiveData<String>()
//		ret.value = "..."
//		return ret
//	}

}