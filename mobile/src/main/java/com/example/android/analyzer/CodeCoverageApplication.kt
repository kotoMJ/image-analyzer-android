package com.example.android.analyzer

import android.app.Activity
import android.app.Application
import android.support.annotation.Nullable
import android.util.Log
import android.util.Log.INFO
import androidx.navigation.NavDeepLinkBuilder
import com.example.android.analyzer.core.ApplicationInterface
import com.example.android.analyzer.core.FeatureCore
import com.example.android.analyzer.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

class CodeCoverageApplication : Application(), ApplicationInterface, HasActivityInjector {

	@Inject
	lateinit var dispatchingActivityAndroidInjector: DispatchingAndroidInjector<Activity>

	override fun activityInjector(): DispatchingAndroidInjector<Activity> = dispatchingActivityAndroidInjector

	override fun navigateHome() {
		val homePendingIntent = NavDeepLinkBuilder(this)
			.setGraph(R.navigation.mobile_navigation)
			.setDestination(R.id.launcher_home)
			.createPendingIntent()
		homePendingIntent.send()
	}

	override fun provideApplication(): Application = this

	override fun getVersionCode() = BuildConfig.VERSION_CODE

	override fun getVersionName() = BuildConfig.VERSION_NAME

	override fun onCreate() {
		super.onCreate()

		if (BuildConfig.DEBUG) {
			Timber.plant(DebugTree())
		} else {
			Timber.plant(CrashReportingTree())
		}

		FeatureCore.init(this)
		AppInjector.init(this)
	}

	/** A tree which logs important information for crash reporting.  */
	private class CrashReportingTree : Timber.Tree() {
		override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
			if (priority == Log.VERBOSE || priority == Log.DEBUG) {
				return
			}

			//TODO FakeCrashLibrary.log(priority, tag, message)

			if (t != null) {
				if (priority == Log.ERROR) {
					//TODO FakeCrashLibrary.logError(t)
				} else if (priority == Log.WARN) {
					//TODO FakeCrashLibrary.logWarning(t)
				}
			}
		}

		fun isLoggable(priority: Int, @Nullable tag: String): Boolean {
			return priority >= INFO
		}
	}
}