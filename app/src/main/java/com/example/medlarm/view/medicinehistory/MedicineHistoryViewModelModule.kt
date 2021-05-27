package com.example.medlarm.view.medicinehistory

import androidx.lifecycle.ViewModel
import com.example.medlarm.di.ViewModelKey
import com.example.medlarm.view.signup.SignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MedicineHistoryViewModelModule {
        @Binds
        @IntoMap
        @ViewModelKey(MedicineHistoryViewModel::class)
        abstract fun bindsMedicineHistoryViewModel(medicineHistoryViewModel: MedicineHistoryViewModel): ViewModel
}