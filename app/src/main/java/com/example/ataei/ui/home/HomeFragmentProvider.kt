package com.example.ataei.ui.home

import com.example.ataei.ui.home.list.HomeListFragment
import com.example.ataei.ui.home.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * All fragments of [HomeActivity] should be provided via this [Module]
 */

@Module
abstract class HomeFragmentProvider {

    @ContributesAndroidInjector
    abstract fun provideHomeListFragment(): HomeListFragment

    @ContributesAndroidInjector
    abstract fun provideProfileFragment(): ProfileFragment

}

