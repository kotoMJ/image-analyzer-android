package com.example.android.analyzer.di

import android.app.Application
import com.example.android.analyzer.CodeCoverageApplication
import com.example.android.analyzer.core.di.FeatureCoreModule
import com.example.android.analyzer.text.di.TextRecognitionDaggerModule
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
	ViewModelModule::class,
	TextRecognitionDaggerModule::class,
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