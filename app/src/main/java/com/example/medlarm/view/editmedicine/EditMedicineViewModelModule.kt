package com.example.medlarm.view.editmedicine

import androidx.lifecycle.ViewModel
import com.example.medlarm.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class EditMedicineViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(EditMedicineViewModel::class)
    abstract fun bindsEditAlarmViewModel(EditAlarmViewModel: EditMedicineViewModel): ViewModel
}