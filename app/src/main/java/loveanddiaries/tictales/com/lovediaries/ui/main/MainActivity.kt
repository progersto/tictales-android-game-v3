package loveanddiaries.tictales.com.lovediaries.ui.main

import android.arch.lifecycle.Observer
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import loveanddiaries.tictales.com.lovediaries.R
import loveanddiaries.tictales.com.lovediaries.base.BaseActivity
import loveanddiaries.tictales.com.lovediaries.utils.Result
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_main)

        viewModel.chaptersProgress.observe(this, Observer {
            when(it?.status){
                Result.ResultStatus.LOADING -> showLoading()
                Result.ResultStatus.ERROR -> {
                    hideLoading()
                    showMessage(it.message)
                }
                Result.ResultStatus.SUCCESS -> {
                    hideLoading()
                    viewModel.toNextChapter()
                    replaceFragment(MainFragment.newInstance())
                }
            }
        })

        viewModel.chapters.observe(this, Observer {
            Log.d("MYCHAPTERS", "Loaded ${it?.size} chapters")
        })

        viewModel.requestChapters()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }
}
