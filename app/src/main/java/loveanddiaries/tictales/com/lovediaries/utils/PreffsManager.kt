package loveanddiaries.tictales.com.lovediaries.utils

import android.content.SharedPreferences

class PreffsManager(private val prefs: SharedPreferences){
    private  val TOKEN_KEY = "token"

    fun saveToken(token: String?){
        prefs.edit().putString(TOKEN_KEY, token).apply()
    }

    fun getToken() = prefs.getString(TOKEN_KEY, "")
}