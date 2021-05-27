package com.example.medlarm.di

import android.app.Application
import com.example.medlarm.app.MedLarm
import com.example.medlarm.datasource.modules.HttpModule
import com.example.medlarm.datasource.modules.RepositoryModule
import com.example.medlarm.datasource.modules.RetrofitModule
import com.example.medlarm.datasource.modules.ServiceModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelFactory::class,
        ActivityBuildersModule::class,
        AppModule::class,
        HttpModule::class,
        RetrofitModule::class,
        ServiceModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent : AndroidInjector<MedLarm> {


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}