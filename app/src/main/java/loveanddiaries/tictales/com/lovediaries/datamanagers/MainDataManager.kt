package loveanddiaries.tictales.com.lovediaries.datamanagers

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import loveanddiaries.tictales.com.lovediaries.network.ApiInterface
import loveanddiaries.tictales.com.lovediaries.network.model.Chapter
import loveanddiaries.tictales.com.lovediaries.network.model.ChaptersResponse
import loveanddiaries.tictales.com.lovediaries.utils.PreffsManager
import loveanddiaries.tictales.com.lovediaries.utils.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface MainDataManager{
    fun getChaptersProgress(): MutableLiveData<Result<Unit>>
    fun requestChapters()

    fun getChapters(): MutableLiveData<MutableList<Chapter>>
}

class MainDataManagerImpl(private val service: ApiInterface, private val preffsManager: PreffsManager): MainDataManager{
    private val chaptersRequestProgress = MutableLiveData<Result<Unit>>()
    private val chapters = MutableLiveData<MutableList<Chapter>>()

    override fun getChaptersProgress() = chaptersRequestProgress
    override fun getChapters() = chapters

    override fun requestChapters() {
        chaptersRequestProgress.value = Result.loading()
        service.getChapters("Bearer ${preffsManager.getToken()}").enqueue(object : Callback<ChaptersResponse>{
            override fun onFailure(call: Call<ChaptersResponse>?, t: Throwable?) {
                chaptersRequestProgress.value = Result.error(t?.message)
            }

            override fun onResponse(call: Call<ChaptersResponse>?, response: Response<ChaptersResponse>?) {
                chapters.value = response?.body()?.payload?.items
                chapters.value?.forEach {
                    Log.d("MYCHAPTERS", "Chapter: $it")
                }
                chaptersRequestProgress.value = Result.success(Unit)
            }
        })
    }
}