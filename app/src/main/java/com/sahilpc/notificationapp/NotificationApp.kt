package com.sahilpc.notificationapp

import android.app.Application
import com.onesignal.OneSignal
import com.sahilpc.notificationapp.ui.data.common.Constants.ONESIGNAL_APP_ID

class NotificationApp:Application() {
    override fun onCreate() {
        super.onCreate()

        // Logging set to help debug issues, remove before releasing your app.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }

}