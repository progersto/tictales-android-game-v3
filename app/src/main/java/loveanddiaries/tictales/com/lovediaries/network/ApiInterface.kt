package loveanddiaries.tictales.com.lovediaries.network

import loveanddiaries.tictales.com.lovediaries.network.model.AuthResponse
import loveanddiaries.tictales.com.lovediaries.network.model.ChaptersResponse
import loveanddiaries.tictales.com.lovediaries.utils.AUTH_URL
import loveanddiaries.tictales.com.lovediaries.utils.CHAPTERS_URL
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @FormUrlEncoded
    @POST(AUTH_URL)
    fun authenticate(@Field("adId") advertisementID: String): Call<AuthResponse>

    @GET(CHAPTERS_URL)
    fun getChapters(@Header("Authorization") header: String): Call<ChaptersResponse>
}