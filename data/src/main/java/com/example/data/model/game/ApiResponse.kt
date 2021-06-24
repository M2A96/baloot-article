package com.example.data.model.game

import com.google.gson.annotations.SerializedName

data class ApiResponse(

    @SerializedName("ClientMessageId")
    val clientMessageId: Int? = null,

    @SerializedName("Count")
    val count: Int,

    @SerializedName("HasError")
    val hasError: Boolean = false,

    @SerializedName("ErrorMessage")
    val errorMessage: String? = null,

    @SerializedName("ErrorCode")
    val errorCode: Int = 0,

    @SerializedName("Result")
    val result: List<Game>
)