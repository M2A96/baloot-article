package com.example.ataei.util.extentions

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment

fun FragmentActivity.findNavHostFragment(@IdRes fragmentId: Int): NavHostFragment =
    supportFragmentManager.findFragmentById(fragmentId) as NavHostFragment


