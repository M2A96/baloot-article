package com.example.ataei.ui.home

import com.example.ataei.ui.home.articles.ArticlesFragment
import com.example.ataei.ui.home.detail.ArticleDetailFragment
import com.example.ataei.ui.home.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * All fragments of [HomeActivity] should be provided via this [Module]
 */

@Module
abstract class HomeFragmentProvider {

    @ContributesAndroidInjector
    abstract fun provideArticlesFragment(): ArticlesFragment

    @ContributesAndroidInjector
    abstract fun provideProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun provideArticleDetailFragment(): ArticleDetailFragment

}

