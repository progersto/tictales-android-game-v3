package loveanddiaries.tictales.com.lovediaries.base

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import loveanddiaries.tictales.com.lovediaries.R
import loveanddiaries.tictales.com.lovediaries.utils.LoadingUiHelper
import loveanddiaries.tictales.com.lovediaries.utils.SingleToast

open class BaseActivity : AppCompatActivity(){

    private var progressDialog: LoadingUiHelper.ProgressDialogFragment? = null

    fun showMessage(message: String?, duration: Int = Toast.LENGTH_SHORT){
        SingleToast.showMessage(this, message, duration)
    }

    fun showLoading(){
        if (progressDialog == null){
            progressDialog = LoadingUiHelper.showProgress()
        }
        progressDialog?.show(supportFragmentManager, LoadingUiHelper.ProgressDialogFragment.TAG)
    }

    fun hideLoading(){
        progressDialog?.dismiss()
        progressDialog = null
    }

    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
    }

    fun addFragment(fragment: Fragment, tag: String){
        supportFragmentManager.beginTransaction()
                .add(R.id.container, fragment)
                .addToBackStack(tag)
                .commit()
    }
}