package kids.dev.mykiddy

import android.app.Application
import android.content.Intent
import kids.dev.mykiddy.data.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import kotlin.system.exitProcess


class MyKiddyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Koin
        startKoin {
            androidContext(this@MyKiddyApplication)
            modules(appModule)
        }

        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            // Handle the uncaught exception here
            println("Uncaught exception occurred in thread ${thread.name}: $throwable")
            // Restart the app by launching the main activity again
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            // Finish the current activity to prevent it from being restarted again
            exitProcess(0)

        }
    }

    override fun onTerminate() {
        super.onTerminate()

        // Stop Koin
        stopKoin()
    }
}