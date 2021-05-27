package com.example.medlarm.view.settings

import androidx.lifecycle.ViewModel
import com.example.medlarm.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SettingsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindsProfileViewModel(settingsViewModel: SettingsViewModel): ViewModel
}