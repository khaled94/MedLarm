package com.example.medlarm.view.changepassword

import androidx.lifecycle.ViewModel
import com.example.medlarm.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ChangePasswordViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ChangePasswordViewModel::class)
    abstract fun bindsChangePasswordViewModel(changePasswordViewModel: ChangePasswordViewModel): ViewModel
}
