package com.example.android.analyzer.di

import android.arch.lifecycle.ViewModel
import com.example.android.analyzer.core.di.ViewModelKey
import com.example.android.analyzer.ui.MainActivity
import com.example.android.analyzer.ui.MainFragment
import com.example.android.analyzer.ui.settings.SettingsCustomFragment
import com.example.android.analyzer.ui.settings.SettingsCustomViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class CodeCoverageDaggerModule {

	@ContributesAndroidInjector(/*modules = [FragmentProvider::class]*/)
	abstract fun contributeMainActivityTroubleMaker(): MainActivity

	@ContributesAndroidInjector
	abstract fun contributeCreateMainFragment(): MainFragment

	@ContributesAndroidInjector
	abstract fun contributeCreateSettingsFragment(): SettingsCustomFragment

	@Binds
	@IntoMap
	@ViewModelKey(SettingsCustomViewModel::class)
	abstract fun bindSettingsViewModel(settingsViewModel: SettingsCustomViewModel): ViewModel

}