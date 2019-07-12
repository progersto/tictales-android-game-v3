package loveanddiaries.tictales.com.lovediaries.ui.main


import android.arch.lifecycle.Observer
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.fragment_main.*

import loveanddiaries.tictales.com.lovediaries.R
import loveanddiaries.tictales.com.lovediaries.base.BaseFragment
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MainFragment : BaseFragment() {

    private val viewModel by sharedViewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        activity?.window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
//        activity?.window?.decorView?.systemUiVisibility = (
//                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.currentChapter.observe(viewLifecycleOwner, Observer {
            showLoading()
            val drawable = bg.drawable



            Glide.with(bg.context).load(it?.media).apply(RequestOptions().placeholder(drawable)).listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    hideLoading()
                    showMessage(e?.message)
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    hideLoading()
                    val nextMedia = viewModel.getNextChapter()
                    if (nextMedia != null) {
                        Glide.with(bg.context).load(nextMedia.media).apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).preload()
                    }

                    return false
                }
            }).into(bg)
            header.text = String.format(getString(R.string.chapter_header), it?.id)
            chapter_name.text = it?.title
        })

        next_button.setOnClickListener {
            val setPrevButton = viewModel.toNextChapter()
            checkPrevVisible()
        }
        prev_button.setOnClickListener {
            viewModel.toPrevChapter()
            checkPrevVisible()
        }
    }


    private fun checkPrevVisible() {
        if (viewModel.getChapterIndex() == 0) {
            prev_button.visibility = View.GONE
            next_button.visibility = View.VISIBLE
        } else  if (viewModel.getChapterIndex() + 1 == viewModel.chapters.value?.size){
            prev_button.visibility = View.VISIBLE
            next_button.visibility = View.GONE
        }else{
            prev_button.visibility = View.VISIBLE
            next_button.visibility = View.VISIBLE
        }
    }


    companion object {
        const val TAG = "MainFragment"

        fun newInstance() = MainFragment()
    }
}
