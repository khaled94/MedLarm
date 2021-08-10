package com.example.medlarm.view.addmedicine

import androidx.lifecycle.ViewModel
import com.example.medlarm.di.ViewModelKey
import com.example.medlarm.view.signup.SignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AddMedicineViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AddMedicineViewModel::class)
    abstract fun bindsAddMedicineViewModel(addMedicine: AddMedicineViewModel): ViewModel
}