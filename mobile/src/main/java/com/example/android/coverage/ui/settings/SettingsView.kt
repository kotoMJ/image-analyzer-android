package com.example.android.coverage.ui.settings

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.core.PreferencesCore
import com.example.android.core.arch.BaseFragment
import com.example.android.core.ktools.vmb
import com.example.android.coverage.R
import com.example.android.coverage.databinding.FragmentSettingsBinding
import kotlinx.android.synthetic.main.fragment_settings.view.textX
import timber.log.Timber
import java.util.Random
import javax.inject.Inject

interface SettingsView {
	fun nextToken()
}

class SettingsFragment : BaseFragment(), SettingsView {

	@Inject
	lateinit var preferencesCore: PreferencesCore

	private val vmb by vmb<SettingsViewModel, FragmentSettingsBinding>(R.layout.fragment_settings) { SettingsViewModel() }

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		//TODO discuss this weird required extra set with Jakub
		vmb.binding.view = this
		vmb.binding.viewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)
		return vmb.rootView
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		vmb.binding.setLifecycleOwner(viewLifecycleOwner)
		vmb.viewModel.token.observe(viewLifecycleOwner, Observer { tokenText ->
			//			vmb.rootView.textX.text = tokenText
			run {
				Timber.d(">>>$tokenText")
				vmb.rootView.textX.text = tokenText
			}
		})
	}

	override fun nextToken() {
		Timber.d(">>>nextToken")
		vmb.viewModel.token.value = Random().nextInt().toString()
	}
}