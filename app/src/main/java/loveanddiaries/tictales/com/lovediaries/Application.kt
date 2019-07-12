package loveanddiaries.tictales.com.lovediaries

import android.app.Application
import loveanddiaries.tictales.com.lovediaries.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Android context
            androidContext(this@Application)
            // modules
            modules(appModules)
        }
    }
}