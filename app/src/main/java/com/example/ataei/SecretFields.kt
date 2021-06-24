package com.example.ataei

import javax.inject.Inject

class SecretFields @Inject constructor() {

    private val debugBaseUrl = "https://newsapi.org/v2/"

    private val releaseBaseUrl = "https://newsapi.org/v2/"

    val apiKey = "a51cefac233347d894f86ac05fb6c647"

    fun getBaseUrl(): String {
        return if (BuildConfig.DEBUG)
            debugBaseUrl
        else
            releaseBaseUrl
    }


}