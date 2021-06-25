package com.example.ataei.ui.spash

import com.example.ataei.R
import com.example.ataei.databinding.ActivitySplashBinding
import com.example.ataei.ui.base.BaseActivity

class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {
    override val viewModel: SplashViewModel by getLazyViewModel()
    override val layoutId: Int
        get() = R.layout.activity_splash

}