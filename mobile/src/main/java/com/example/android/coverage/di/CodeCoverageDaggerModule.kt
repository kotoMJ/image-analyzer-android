package com.example.android.coverage.di

import com.example.android.coverage.ui.MainActivity
import com.example.android.coverage.ui.MainFragment
import com.example.android.coverage.ui.settings.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CodeCoverageDaggerModule {

	@ContributesAndroidInjector(/*modules = [FragmentProvider::class]*/)
	abstract fun contributeMainActivityTroubleMaker(): MainActivity

	@ContributesAndroidInjector
	abstract fun contributeCreateMainFragment(): MainFragment

	@ContributesAndroidInjector
	abstract fun contributeCreateSettingsFragment(): SettingsFragment

}