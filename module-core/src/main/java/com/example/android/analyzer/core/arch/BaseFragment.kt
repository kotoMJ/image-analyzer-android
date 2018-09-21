package com.example.android.analyzer.core.arch

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.android.analyzer.core.R
import com.example.android.analyzer.core.di.Injectable
import javax.inject.Inject

abstract class BaseFragment : Fragment(), Injectable {

	companion object {
		const val SNACK_BAR_MAX_LINES_DEFAULT = 4
	}

	@Inject
	lateinit var viewModelFactory: ViewModelProvider.Factory

	var lastSnackbar: Snackbar? = null

	inline fun <reified VM : ViewModel> findViewModel(viewModel: Class<VM>, ofLifecycleOwner: Fragment = this, factory: ViewModelProvider.Factory = viewModelFactory) = ViewModelProviders.of(ofLifecycleOwner, factory).get(viewModel)

	fun showToast(message: String, withOffset: Boolean = true) {
		Toast.makeText(activity, message, Toast.LENGTH_SHORT).apply {
			if (withOffset) {
				setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 2 * getResources().getDimensionPixelOffset(R.dimen.global_bottom_bar_height))
			}
		}.show()
	}

	fun showSnackbar(view: View, @StringRes stringRes: Int, length: Int = Snackbar.LENGTH_LONG, maxLines: Int = SNACK_BAR_MAX_LINES_DEFAULT, config: (Snackbar.() -> Unit)? = null) {
		showSnackbar(view, view.context.getString(stringRes), length, maxLines, config)
	}

	fun showSnackbar(view: View, message: String, length: Int = Snackbar.LENGTH_LONG, maxLines: Int = SNACK_BAR_MAX_LINES_DEFAULT, config: (Snackbar.() -> Unit)? = null) {
		val newSnackbar = Snackbar.make(view, message, length).apply { config?.invoke(this) }
		newSnackbar.view.findViewById<TextView>(android.support.design.R.id.snackbar_text)?.maxLines = maxLines
		lastSnackbar?.dismiss()
		lastSnackbar = newSnackbar
		newSnackbar.show()
	}

}