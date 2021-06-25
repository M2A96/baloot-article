package com.example.ataei.ui.spash

import androidx.fragment.app.FragmentActivity
import com.example.ataei.ui.base.BaseNavigator
import com.example.ataei.ui.home.HomeActivity
import javax.inject.Inject

class SplashNavigator @Inject constructor(
) : BaseNavigator {
    fun navigateToHomeActivity(activity: FragmentActivity) {
        startActivity(activity, HomeActivity::class.java)
    }
}