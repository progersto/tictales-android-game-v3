package loveanddiaries.tictales.com.lovediaries.datamanager

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.util.Log
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import loveanddiaries.tictales.com.lovediaries.network.ApiInterface
import loveanddiaries.tictales.com.lovediaries.network.model.AuthResponse
import loveanddiaries.tictales.com.lovediaries.network.model.Payload
import loveanddiaries.tictales.com.lovediaries.utils.PreffsManager
import loveanddiaries.tictales.com.lovediaries.utils.Result
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface AuthDataManager{
    fun getAuthenticationProgress(): MutableLiveData<Result<AuthResponse>>
    fun authenticate()

    fun getPayload(): MutableLiveData<Payload<Unit>>
}

class AuthDataManagerImpl(private val service: ApiInterface, private val context: Context, private val preffsManager: PreffsManager): AuthDataManager{
    private val authenticationProgress = MutableLiveData<Result<AuthResponse>>()
    private val payloadResponse = MutableLiveData<Payload<Unit>>()

    override fun getAuthenticationProgress() = authenticationProgress

    override fun getPayload() = payloadResponse

    override fun authenticate() {
        authenticationProgress.value = Result.loading()
        doAsync {
            val adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context)
            val adid = adInfo.id

            service.authenticate(adid).enqueue(object : Callback<AuthResponse> {

                override fun onFailure(call: Call<AuthResponse>?, t: Throwable?) {
                    authenticationProgress.value = Result.error(t?.message)
                }

                override fun onResponse(call: Call<AuthResponse>?, response: Response<AuthResponse>?) {
                    authenticationProgress.value = Result.success(response?.body())
                    preffsManager.saveToken(response?.body()?.payload?.token)
                    payloadResponse.value = response?.body()?.payload
                }
            })
        }
    }
}