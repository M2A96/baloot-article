package com.example.ataei.ui.home.profile

import android.util.Log
import com.example.ataei.ui.base.BaseViewModel
import com.example.ataei.ui.home.HomeNavigator
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val homeNavigator: HomeNavigator
) : BaseViewModel() {

    private val TAG = "ProfileViewModel"
    fun OnGitHubClicked() {
        Log.d(TAG, "OnGitHubClicked: ")
        activityAction {
            homeNavigator.openUrl(it, myGithubLink)
        }
    }

    fun onLinkedInClicked() {
        Log.d(TAG, "onLinkedInClicked: ")
        activityAction {
            homeNavigator.openUrl(it, myLinkedIn)
        }
    }

    companion object {
        private const val myGithubLink = "https://github.com/Mohsenataei"

        private const val myLinkedIn = "https://www.linkedin.com/in/mohsen-ataei/"
    }
}