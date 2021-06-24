package com.example.ataei.di.builder

import com.example.ataei.ui.home.HomeActivity
import com.example.ataei.ui.home.HomeFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * The Main Module for binding all of activities.
 * Every Activity should contribute with it's related modules
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(HomeFragmentProvider::class)])
    internal abstract fun bindMainActivity(): HomeActivity
}
