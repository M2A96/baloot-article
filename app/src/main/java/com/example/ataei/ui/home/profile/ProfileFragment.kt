package com.example.ataei.ui.home.profile

import com.example.ataei.R
import com.example.ataei.databinding.FragmentProfileBinding
import com.example.ataei.ui.base.BaseFragment
import com.example.ataei.ui.base.ViewModelScope
import javax.inject.Inject

class ProfileFragment @Inject constructor() :
    BaseFragment<ProfileViewModel, FragmentProfileBinding>() {
    override val viewModel: ProfileViewModel by getLazyViewModel(ViewModelScope.FRAGMENT)
    override val layoutId: Int
        get() = R.layout.fragment_profile

}