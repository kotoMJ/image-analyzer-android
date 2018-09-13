package com.example.android.analyzer.text.di

import com.example.android.analyzer.text.ui.TextRecognitionFlowFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TextRecognitionDaggerModule {

	@ContributesAndroidInjector
	abstract fun contributeFlowStepFragment(): TextRecognitionFlowFragment
}