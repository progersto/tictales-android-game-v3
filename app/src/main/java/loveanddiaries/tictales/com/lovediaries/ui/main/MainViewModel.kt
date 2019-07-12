package loveanddiaries.tictales.com.lovediaries.ui.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import loveanddiaries.tictales.com.lovediaries.datamanagers.MainDataManager
import loveanddiaries.tictales.com.lovediaries.network.model.Chapter

class MainViewModel(private val dataManager: MainDataManager) : ViewModel() {
    val chaptersProgress = dataManager.getChaptersProgress()
    val chapters = dataManager.getChapters()

    val currentChapter = MutableLiveData<Chapter>()
    private var chapterIndex = 0

    fun getChapterIndex(): Int{
        return chapterIndex
    }

    fun requestChapters() {
        dataManager.requestChapters()
    }

    fun getNextChapter(): Chapter? {
        var nextChapter: Chapter? = null
        chapters.value?.also {
            if (chapterIndex + 1 < it.size) {
                nextChapter = it[chapterIndex + 1]
            }
        }
        return nextChapter
    }


    fun toNextChapter(){
        chapters.value?.also {
            if (it.isNotEmpty()) {
                if (currentChapter.value == null) {
                    chapterIndex = 0
                    currentChapter.value = it[chapterIndex]
                } else {
                    if (chapterIndex + 1 < it.size) {
                        chapterIndex++
                        currentChapter.value = it[chapterIndex]
                    }
                }
            }
        }
    }


    fun toPrevChapter(){
        chapters.value?.also {
            if (it.isNotEmpty()) {
                if (chapterIndex > 0) {
                    chapterIndex--
                    currentChapter.value = it[chapterIndex]
                }
            }
        }
    }
}