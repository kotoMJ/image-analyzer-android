package com.example.android.analyzer.ui.settings

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.analyzer.R
import com.example.android.analyzer.core.arch.BaseFragment
import com.example.android.analyzer.core.ktools.vmb
import com.example.android.analyzer.databinding.FragmentSettingsCustomBinding
import timber.log.Timber

interface SettingsCustomView {
	fun onShowProfileClick()
	fun onNotificationsClick()
	fun onShowAccountClick()
	fun onArchClick()
}

class SettingsCustomFragment : BaseFragment(), SettingsCustomView {

	private val vmb by vmb<SettingsCustomViewModel, FragmentSettingsCustomBinding>(R.layout.fragment_settings_custom) { findViewModel(SettingsCustomViewModel::class.java) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		//TODO discuss this weird required extra set with Jakub
		vmb.binding.view = this
		vmb.binding.viewModel = findViewModel(SettingsCustomViewModel::class.java)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return vmb.rootView
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		vmb.binding.setLifecycleOwner(viewLifecycleOwner)
		vmb.viewModel.token.observe(viewLifecycleOwner, Observer { tokenText ->
			//			vmb.rootView.textX.text = tokenText
			run {
				Timber.d(">>>$tokenText")
				//vmb.rootView.textX.text = tokenText
			}
		})
	}

	override fun onShowProfileClick() {
		showToast(getString(R.string.message_not_implemented))
	}

	override fun onNotificationsClick() {
		showToast(getString(R.string.message_not_implemented))
	}

	override fun onShowAccountClick() {
		showToast(getString(R.string.message_not_implemented))
	}

	override fun onArchClick() {
		showToast(getString(R.string.message_not_implemented))
	}
}