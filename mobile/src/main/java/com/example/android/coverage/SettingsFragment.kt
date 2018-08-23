package com.example.android.coverage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.core.PreferencesCore
import com.example.android.core.arch.BaseFragment
import timber.log.Timber
import javax.inject.Inject

class SettingsFragment : BaseFragment() {

	@Inject
	lateinit var preferencesCore: PreferencesCore

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?): View? {

		Timber.d(">>>${preferencesCore.sampleToken}")

		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_settings, container, false)
	}

}