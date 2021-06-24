package com.example.ataei.di.module

import android.app.Application
import android.content.Context
import com.example.ataei.app.GameApplication
import com.example.ataei.di.builder.ViewModelBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [ViewModelBuilder::class])
object AppModule {

    @Provides
    @Singleton
    fun provideContext(app: GameApplication): Context = app


}