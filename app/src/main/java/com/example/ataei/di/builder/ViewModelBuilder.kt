package com.example.ataei.di.builder

import androidx.lifecycle.ViewModelProvider
import com.example.ataei.ui.home.HomeViewModelBuilder
import com.example.ataei.ui.base.GameViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * With this module all of ViewModels binds into generated Map<Class, ViewModel> and the map
 * will be injected in [GameViewModelFactory]. In order to do this, we have to bind all
 * ViewModelBuilder modules in this module.
 *
 * And finally [GameViewModelFactory] will be provided as [ViewModelProvider.Factory].
 *
 */

@Module(
    includes = [
        (HomeViewModelBuilder::class)
    ]
)
abstract class ViewModelBuilder {

    @Binds
    abstract fun bindViewModelFactory(gameViewModelFactory: GameViewModelFactory): ViewModelProvider.Factory
}


