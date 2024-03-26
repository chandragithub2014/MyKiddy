package kids.dev.mykiddy

import android.app.Application
import kids.dev.mykiddy.data.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin


class MyKiddyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Koin
        startKoin {
            androidContext(this@MyKiddyApplication)
            modules(appModule)
        }
    }

    override fun onTerminate() {
        super.onTerminate()

        // Stop Koin
        stopKoin()
    }
}