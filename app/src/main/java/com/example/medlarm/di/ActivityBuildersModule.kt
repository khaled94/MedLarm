package com.example.medlarm.di

import com.example.medlarm.view.aboutus.AboutUsActivity
import com.example.medlarm.view.aboutus.AboutUsViewModelModule
import com.example.medlarm.view.addmedicine.AddMedicineActivity
import com.example.medlarm.view.addmedicine.AddMedicineViewModelModule
import com.example.medlarm.view.alarm.AlarmActivity
import com.example.medlarm.view.alarm.RingActivity
import com.example.medlarm.view.medicinehistory.MedicineHistoryActivity
import com.example.medlarm.view.changepassword.ChangePasswordActivity
import com.example.medlarm.view.changepassword.ChangePasswordViewModelModule
import com.example.medlarm.view.contactus.ContactUsActivity
import com.example.medlarm.view.contactus.ContactUsViewModelModule
import com.example.medlarm.view.editmedicine.EditMedicineActivity
import com.example.medlarm.view.editmedicine.EditMedicineViewModelModule
import com.example.medlarm.view.editprofile.EditProfileActivity
import com.example.medlarm.view.editprofile.EditProfileViewModelModule
import com.example.medlarm.view.home.HomeActivity
import com.example.medlarm.view.home.HomeViewModelModule
import com.example.medlarm.view.language.ChooseLanguageActivity
import com.example.medlarm.view.login.LoginActivity
import com.example.medlarm.view.login.LoginViewModelModule
import com.example.medlarm.view.medicinehistory.MedicineHistoryViewModelModule
import com.example.medlarm.view.passwordrecovery.PasswordRecoveryActivity
import com.example.medlarm.view.passwordrecovery.PasswordRecoveryViewModelModule
import com.example.medlarm.view.settings.SettingsActivity
import com.example.medlarm.view.settings.SettingsViewModelModule
import com.example.medlarm.view.signup.SignUpActivity
import com.example.medlarm.view.signup.SignUpViewModelModule
import com.example.medlarm.view.splash.SplashActivity
import com.example.medlarm.view.userhistory.UserHistoryActivity
import com.example.medlarm.view.userhistory.UserHistoryViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

   @ContributesAndroidInjector
   abstract fun contributeSplashActivity() : SplashActivity

   @ContributesAndroidInjector(modules = [SignUpViewModelModule::class])
   abstract fun contributeSignUpActivity(): SignUpActivity

   @ContributesAndroidInjector(modules = [LoginViewModelModule::class])
   abstract fun contributeLoginActivity(): LoginActivity

   @ContributesAndroidInjector(modules = [HomeViewModelModule::class])
   abstract fun contributeHomeActivity(): HomeActivity

   @ContributesAndroidInjector(modules = [ChangePasswordViewModelModule::class])
   abstract fun contributeChangePasswordActivity(): ChangePasswordActivity

   @ContributesAndroidInjector(modules = [AddMedicineViewModelModule::class])
   abstract fun contributeAddMedicineActivity(): AddMedicineActivity

   @ContributesAndroidInjector(modules = [SettingsViewModelModule::class])
   abstract fun contributeSettingsActivity(): SettingsActivity

   @ContributesAndroidInjector(modules = [MedicineHistoryViewModelModule::class])
   abstract fun contributeMedicineHistoryActivity(): MedicineHistoryActivity

   @ContributesAndroidInjector(modules = [UserHistoryViewModelModule::class])
   abstract fun contributeUserHistoryActivity(): UserHistoryActivity

   @ContributesAndroidInjector(modules = [PasswordRecoveryViewModelModule::class])
   abstract fun contributePasswordRecoveryActivity(): PasswordRecoveryActivity

   @ContributesAndroidInjector(modules = [EditMedicineViewModelModule::class])
   abstract fun contributeEditMedicineActivity(): EditMedicineActivity

   @ContributesAndroidInjector(modules = [EditProfileViewModelModule::class])
   abstract fun contributeEditProfileActivity(): EditProfileActivity

   @ContributesAndroidInjector(modules = [AboutUsViewModelModule::class])
   abstract fun contributeAboutUsActivity(): AboutUsActivity

   @ContributesAndroidInjector(modules = [ContactUsViewModelModule::class])
   abstract fun contributeContactUsActivity(): ContactUsActivity

   @ContributesAndroidInjector()
   abstract fun contributeChooseLanguageActivity(): ChooseLanguageActivity

   @ContributesAndroidInjector()
   abstract fun contributeAlarmActivity(): AlarmActivity

   @ContributesAndroidInjector()
   abstract fun contributeRingActivity(): RingActivity
}