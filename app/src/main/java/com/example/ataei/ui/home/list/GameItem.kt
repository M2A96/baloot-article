package com.example.ataei.ui.home.list

import androidx.databinding.BindingAdapter
import com.example.ataei.ui.custom.MagicalImageView

data class GameItem(
    val name: String,
    val icon: String,
    val producer: String,
    val rate: Int = 0
){

//    @BindingAdapter("imageUrl")
//    fun bindImageUrl(imageView: MagicalImageView, imageUrl: String) {
//        imageView.setImageUrl(imageUrl)
//    }
}