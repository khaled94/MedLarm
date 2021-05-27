package com.example.medlarm.view.aboutus

import androidx.lifecycle.ViewModel
import com.example.medlarm.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AboutUsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AboutUsViewModel::class)
    abstract fun bindsAboutUsViewModel(AboutUsViewModel: AboutUsViewModel): ViewModel
}