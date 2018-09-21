package com.example.android.analyzer.text.di

import android.arch.lifecycle.ViewModel
import com.example.android.analyzer.core.di.ViewModelKey
import com.example.android.analyzer.text.ui.TextRecognitionFragment
import com.example.android.analyzer.text.ui.TextRecognitionViewModel
import com.example.android.analyzer.text.ui.TextSourceDefinitionFragment
import com.example.android.analyzer.text.ui.TextSourceDefinitionViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class TextRecognitionDaggerModule {

	@ContributesAndroidInjector
	abstract fun contributeSourceDefinitionFragment(): TextSourceDefinitionFragment

	@Binds
	@IntoMap
	@ViewModelKey(TextSourceDefinitionViewModel::class)
	abstract fun bindSourceDefinitionViewModel(sourceDefinitionViewModel: TextSourceDefinitionViewModel): ViewModel

	@ContributesAndroidInjector
	abstract fun contributeRecognitionFragment(): TextRecognitionFragment

	@Binds
	@IntoMap
	@ViewModelKey(TextRecognitionViewModel::class)
	abstract fun bindRecognitionViewModel(recognitionViewModel: TextRecognitionViewModel): ViewModel
}