package com.example.android.coverage.di

import com.example.android.coverage.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentProvider {

	@ContributesAndroidInjector
	abstract fun contributeCreateSettingsFragment(): SettingsFragment
}