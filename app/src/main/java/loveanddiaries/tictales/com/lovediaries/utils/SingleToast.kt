package loveanddiaries.tictales.com.lovediaries.utils

import android.content.Context
import android.widget.Toast

class SingleToast private constructor(){
    companion object {
        private var toast: Toast? = null

        fun showMessage(context: Context, message: String?, duration: Int){
            toast?.cancel()
            toast = Toast.makeText(context, message, duration)
            toast?.show()
        }
    }
}