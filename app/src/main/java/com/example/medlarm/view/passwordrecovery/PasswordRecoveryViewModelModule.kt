package com.example.medlarm.view.passwordrecovery

import androidx.lifecycle.ViewModel
import com.example.medlarm.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PasswordRecoveryViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PasswordRecoveryViewModel::class)
    abstract fun bindsPasswordRecoveryViewModel(PasswordRecoveryViewModel: PasswordRecoveryViewModel): ViewModel
}