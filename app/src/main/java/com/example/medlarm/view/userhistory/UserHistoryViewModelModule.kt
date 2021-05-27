package com.example.medlarm.view.userhistory

import androidx.lifecycle.ViewModel
import com.example.medlarm.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class UserHistoryViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserHistoryViewModel::class)
    abstract fun bindsUserHistoryViewModel(userHistoryViewModel: UserHistoryViewModel): ViewModel
}