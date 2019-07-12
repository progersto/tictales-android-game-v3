package loveanddiaries.tictales.com.lovediaries.utils

import android.content.res.Resources

object DimenUtils{
    fun dpToPx(dp: Float): Int {
        val density = Resources.getSystem().displayMetrics.density
        return Math.round(dp * density)
    }
}