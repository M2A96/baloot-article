package com.example.ataei.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ataei.R
import com.example.ataei.databinding.ActivityMainBinding
import com.example.ataei.ui.base.BaseActivity

class HomeActivity : BaseActivity<HomeViewModel, ActivityMainBinding>() {

    override val layoutId: Int = R.layout.activity_main
    override val viewModel: HomeViewModel by getLazyViewModel()
}