package com.example.ataei

import javax.inject.Inject

class SecretFields @Inject constructor() {

    private val debugBaseUrl = "https://service-play.pod.ir/"

    private val releaseBaseUrl = "https://service-play.pod.ir/"

    val apiKey = ""

    fun getBaseUrl(): String {
        return if (BuildConfig.DEBUG)
            debugBaseUrl
        else
            releaseBaseUrl
    }


}