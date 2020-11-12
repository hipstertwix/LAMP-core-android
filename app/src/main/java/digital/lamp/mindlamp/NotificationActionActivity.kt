package digital.lamp.mindlamp

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import digital.lamp.mindlamp.appstate.AppState
import digital.lamp.mindlamp.network.model.NotificationData
import digital.lamp.mindlamp.network.model.NotificationEventRequest
import digital.lamp.mindlamp.repository.HomeRepository
import digital.lamp.mindlamp.utils.AppConstants
import digital.lamp.mindlamp.utils.LampLog
import digital.lamp.mindlamp.utils.Utils
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_webview_overview.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NotificationActionActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview_overview)

        val surveyUrl = intent.getStringExtra("survey_path")
        val notificationId = intent.getIntExtra("notification_id", AppConstants.NOTIFICATION_ID)
        val remoteMessage = intent.getStringExtra("remote_message")

        val oSurveyUrl = BuildConfig.BASE_URL_WEB+surveyUrl+"?a="+Utils.toBase64(AppState.session.token + ":" + AppState.session.serverAddress.removePrefix("https://").removePrefix("http://"))

        webviewOverview.clearCache(true)
        webviewOverview.clearHistory()
        webviewOverview.settings.javaScriptEnabled = true
        webviewOverview.settings.domStorageEnabled = true
        webviewOverview.loadUrl(oSurveyUrl);

        NotificationManagerCompat.from(this).cancel(notificationId)

        //Call Analytics API
        if (AppState.session.isLoggedIn) {
            val notificationData =
                NotificationData("notification", "Open App", remoteMessage)
            val notificationEvent = NotificationEventRequest(
                notificationData,
                "lamp.analytics",
                System.currentTimeMillis()
            )
            invokeNotificationData(notificationEvent)
        }
    }

    private fun invokeNotificationData(notificationEventRequest: NotificationEventRequest) {
        val homeRepository = HomeRepository()
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = homeRepository.addNotificationData(
                    AppState.session.userId,
                    notificationEventRequest
                )
                LampLog.e("TAG", " : $response")

            } catch (er: Exception) {
                er.printStackTrace()
            }
        }
    }
}