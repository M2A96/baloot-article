package com.example.ataei.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.ataei.R
import com.example.ataei.ui.base.BaseNavigator
import javax.inject.Inject

/**
 * You can handle navigation between activity and fragments in Home fragment here using methods in [BaseNavigator]
 * */
class HomeNavigator @Inject constructor() : BaseNavigator {

    fun navigateToArticleDetail(fragment: Fragment) {
        navigateTo(fragment, R.id.articlesListToArticleDetailAction)
    }

}