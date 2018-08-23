package com.example.android.coverage.di

import com.example.android.coverage.MainActivity
import com.example.android.coverage.MainFragment
import com.example.android.coverage.SettingsFragment
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