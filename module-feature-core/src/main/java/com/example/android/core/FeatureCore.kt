package com.example.android.core

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.arch.lifecycle.ProcessLifecycleOwner
import timber.log.Timber

interface ApplicationInterface {
	fun navigateHome()
}

object FeatureCore : LifecycleObserver {

	var isAppInBackground: Boolean = false
		private set

	lateinit var applicationInterface: ApplicationInterface

	@OnLifecycleEvent(Lifecycle.Event.ON_START)
	internal fun onAppStart() {
		isAppInBackground = false
		Timber.d("APP is in foreground")
	}

	@OnLifecycleEvent(Lifecycle.Event.ON_STOP)
	internal fun onAppStop() {
		isAppInBackground = true
		Timber.d("APP is in background")
	}

	fun init(applicationInterface: ApplicationInterface) {
		this.applicationInterface = applicationInterface
		ProcessLifecycleOwner.get().lifecycle.addObserver(this)
	}

}