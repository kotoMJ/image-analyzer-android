package com.example.android.core.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.android.core.PreferencesCore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class FeatureCoreModule {
	@Provides
	@Singleton
	open fun provideAppContext(application: Application): Context {
		return application.applicationContext
	}

	@Provides
	@Singleton
	fun provideSharedPreferences(context: Context): SharedPreferences {
		return PreferenceManager.getDefaultSharedPreferences(context)
	}

	@Provides
	@Singleton
	fun provideCorePreferences(context: Context, sharedPreferences: SharedPreferences): PreferencesCore {
		return PreferencesCore(context, sharedPreferences)
	}

}