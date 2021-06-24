package com.example.ataei.di.module

import androidx.core.content.FileProvider
import com.example.ataei.util.provider.BaseResourceProvider
import com.example.ataei.util.provider.ResourceProvider
import com.example.data.source.local.file.BaseFileProvider
import dagger.Binds
import dagger.Module

@Module
interface UtilModule {
    /**
     * Provide main implementation of [BaseResourceProvider] to access app raw resources
     */
    @Binds
    fun bindResourceProvider(resourceProvider: ResourceProvider): BaseResourceProvider

    /**
     * Provide main implementation of [BaseFileProvider]
     */
    @Binds
    fun bindFileProvider(fileProvider: FileProvider): BaseFileProvider
}