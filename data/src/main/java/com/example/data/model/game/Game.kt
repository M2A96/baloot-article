package com.example.data.model.game

import com.google.gson.annotations.SerializedName

data class Game(

    @SerializedName("Name")
    val name: String,

    @SerializedName("business")
    val business: Business,

    @SerializedName("preview")
    val preview: String
)