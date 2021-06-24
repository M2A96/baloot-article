package com.example.ataei.di.component

import android.content.Context
import com.example.ataei.app.GameApplication
import com.example.ataei.di.builder.ActivityBuilder
import com.example.ataei.di.module.AppModule
import com.example.ataei.di.module.NetworkModule
import com.example.ataei.di.module.UtilModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Main Application [Component] that included all of modules and sub components.
 */

@Singleton
@Component(
    modules = [
        (NetworkModule::class),
        (AppModule::class),
        (AndroidInjectionModule::class),
        (ActivityBuilder::class),
        (UtilModule::class)
    ]
)

interface AppComponent : AndroidInjector<GameApplication> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<GameApplication> {
        interface Factory {
            fun create(@BindsInstance application: Context): AppComponent
        }
    }
}