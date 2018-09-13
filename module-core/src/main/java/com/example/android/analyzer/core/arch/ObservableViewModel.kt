package com.example.android.core.arch

import android.arch.lifecycle.ViewModel
import android.databinding.Observable
import android.databinding.PropertyChangeRegistry

abstract class ObservableViewModel : ViewModel(), android.databinding.Observable {

	@Transient
	private val observableCallbacks: PropertyChangeRegistry = PropertyChangeRegistry()

	@Synchronized
	override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
		observableCallbacks.add(callback)
	}

	@Synchronized
	override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
		observableCallbacks.remove(callback)
	}

}