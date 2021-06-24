package com.example.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class ResponseWrapperDto<T : Any>(
    @SerializedName("articles")
    val data: T,

    @SerializedName("status")
    val status: String,

    @SerializedName("totalResults")
    val totalResults: Int


)