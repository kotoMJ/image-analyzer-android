package com.example.android.analyzer.text.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.android.analyzer.core.FeatureCore
import com.example.android.analyzer.core.arch.BaseFragment
import com.example.android.analyzer.core.ktools.vmb
import com.example.android.analyzer.text.R
import com.example.android.analyzer.text.databinding.FragmentTextRecognitionBinding

interface TextRecognitionView

class TextRecognitionFragment : BaseFragment(), TextRecognitionView {

	private val vmb by vmb<TextRecognitionViewModel, FragmentTextRecognitionBinding>(R.layout.fragment_text_recognition) { findViewModel(TextRecognitionViewModel::class.java) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		vmb.binding.view = this
		vmb.binding.viewModel = findViewModel(TextRecognitionViewModel::class.java)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return vmb.rootView
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		vmb.binding.setLifecycleOwner(viewLifecycleOwner)

		view.findViewById<View>(R.id.next_button).setOnClickListener { FeatureCore.applicationInterface.navigateHome() }
	}

}