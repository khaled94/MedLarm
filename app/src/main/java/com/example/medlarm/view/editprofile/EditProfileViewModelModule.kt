package com.example.medlarm.view.editprofile

import androidx.lifecycle.ViewModel
import com.example.medlarm.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class EditProfileViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(EditProfileViewModel::class)
    abstract fun bindsEditProfileViewModel(EditProfileViewModel: EditProfileViewModel): ViewModel
}