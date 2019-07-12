package loveanddiaries.tictales.com.lovediaries.ui.auth

import android.arch.lifecycle.ViewModel
import loveanddiaries.tictales.com.lovediaries.datamanager.AuthDataManager
import loveanddiaries.tictales.com.lovediaries.utils.SingleLiveEvent

class AuthViewModel(private val dataManager: AuthDataManager): ViewModel(){
    val goToMain = SingleLiveEvent<Unit>()

    val authProgress = dataManager.getAuthenticationProgress()
    val payload = dataManager.getPayload()

    fun authenticate(){
        dataManager.authenticate()
    }

    fun onGoToMain(){
        goToMain.call()
    }
}