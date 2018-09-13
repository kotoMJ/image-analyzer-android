package com.example.android.analyzer.di

import com.example.android.analyzer.ui.settings.SettingsCustomFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentProvider {

	@ContributesAndroidInjector
	abstract fun contributeCreateSettingsFragment(): SettingsCustomFragment
}