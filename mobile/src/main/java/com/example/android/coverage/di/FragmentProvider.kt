package com.example.android.coverage.di

import com.example.android.coverage.ui.settings.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentProvider {

	@ContributesAndroidInjector
	abstract fun contributeCreateSettingsFragment(): SettingsFragment
}