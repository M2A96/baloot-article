package com.example.ataei.ui.spash

import androidx.lifecycle.ViewModel
import com.example.ataei.di.builder.ViewModelKey
import com.example.ataei.ui.home.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class SplashViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel
}