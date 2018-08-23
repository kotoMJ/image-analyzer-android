package com.example.android.core.arch

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

	@Inject
	lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

	override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
		return dispatchingAndroidInjector
	}

}