package com.example.medlarm.view.contactus

import androidx.lifecycle.ViewModel
import com.example.medlarm.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ContactUsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ContactUsViewModel::class)
    abstract fun bindsContactUsViewModel(ContactUsViewModel: ContactUsViewModel): ViewModel
}