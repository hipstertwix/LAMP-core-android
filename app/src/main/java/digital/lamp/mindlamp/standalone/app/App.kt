package digital.lamp.mindlamp.standalone.app

import android.app.Application
import digital.lamp.mindlamp.standalone.appstate.AppState
import digital.lamp.mindlamp.standalone.appstate.Pref

import java.io.PrintWriter
import java.io.StringWriter


class App: Application() {

    companion object {
        lateinit var app: App
    }
    override fun onCreate() {
        super.onCreate()
        app = this

        // Initializing Shared pref
        Pref.init(this,
            AppKeys.APP_PREF_NAME
        )

        // Setup handler for uncaught exceptions.
        // Setup handler for uncaught exceptions.
        Thread.setDefaultUncaughtExceptionHandler { thread, e ->
            handleUncaughtException(
                thread,
                e
            )
        }
    }

    private fun handleUncaughtException(thread: Thread?, e: Throwable) {
        e.printStackTrace() // not all Android versions will print the stack trace automatically

        val sw = StringWriter()
        val pw = PrintWriter(sw)
        e.printStackTrace(pw)
        val sStackTrace = sw.toString() // stack trace as a string

        AppState.session.crashValue = sStackTrace
    }
}