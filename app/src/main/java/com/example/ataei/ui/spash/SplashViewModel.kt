package com.example.ataei.ui.spash

import androidx.lifecycle.viewModelScope
import com.example.ataei.ui.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val splashNavigator: SplashNavigator
) : BaseViewModel() {


    init {
        navigateToHomeActivity()
    }

    private fun navigateToHomeActivity() {
        viewModelScope.launch {
            delay(100)
            activityAction {
                splashNavigator.navigateToHomeActivity(it)
            }
        }
    }
}