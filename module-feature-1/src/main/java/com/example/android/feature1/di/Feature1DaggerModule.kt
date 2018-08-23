package com.example.android.feature1.di

import com.example.android.feature1.FlowStepFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class Feature1DaggerModule {

	@ContributesAndroidInjector
	abstract fun contributeFlowStepFragment(): FlowStepFragment
}