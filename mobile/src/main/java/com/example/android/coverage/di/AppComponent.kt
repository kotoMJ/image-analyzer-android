package com.example.android.coverage.di

import android.app.Application
import com.example.android.core.di.FeatureCoreModule
import com.example.android.coverage.CodeCoverageApplication
import com.example.android.feature1.di.Feature1DaggerModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
	AndroidInjectionModule::class,
	AndroidSupportInjectionModule::class,
	FeatureCoreModule::class,
	Feature1DaggerModule::class,
	CodeCoverageDaggerModule::class
])
interface AppComponent {
	@Component.Builder
	interface Builder {
		@BindsInstance
		fun application(application: Application): Builder

		fun build(): AppComponent
	}

	fun inject(codeCoverageApplication: CodeCoverageApplication)

}