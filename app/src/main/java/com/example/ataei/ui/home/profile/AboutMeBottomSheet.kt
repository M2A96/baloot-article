package com.example.ataei.ui.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.example.ataei.R
import com.example.ataei.databinding.AboutMeBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AboutMeBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: AboutMeBottomSheetBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.about_me_bottom_sheet, container, false)
        return binding.root
    }


    companion object {
        fun getNewInstance() = AboutMeBottomSheet()
    }

    override fun show(manager: FragmentManager, tag: String?) {
        super.show(manager, tag)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}