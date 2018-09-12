package com.example.android.core.arch

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.view.Gravity
import android.widget.Toast
import com.example.android.core.R
import com.example.android.core.di.Injectable
import javax.inject.Inject

abstract class BaseFragment : Fragment(), Injectable {

	@Inject
	lateinit var viewModelFactory: ViewModelProvider.Factory

	inline fun <reified VM : ViewModel> findViewModel(viewModel: Class<VM>, ofLifecycleOwner: Fragment = this, factory: ViewModelProvider.Factory = viewModelFactory) = ViewModelProviders.of(ofLifecycleOwner, factory).get(viewModel)

	fun showToast(message: String, withOffset: Boolean = true) {
		Toast.makeText(activity, message, Toast.LENGTH_SHORT).apply {
			if (withOffset) {
				setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 2 * getResources().getDimensionPixelOffset(R.dimen.global_bottom_bar_height))
			}
		}.show()
	}

}