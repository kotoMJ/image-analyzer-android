package com.example.android.coverage.di

import com.example.android.coverage.ui.settings.SettingsCustomFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentProvider {

	@ContributesAndroidInjector
	abstract fun contributeCreateSettingsFragment(): SettingsCustomFragment
}