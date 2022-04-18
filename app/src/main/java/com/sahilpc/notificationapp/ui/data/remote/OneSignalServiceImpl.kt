package com.sahilpc.notificationapp.ui.data.remote

import android.app.Notification
import com.sahilpc.notificationapp.ui.data.common.Constants.API_KEY
import com.sahilpc.notificationapp.ui.data.common.Constants.NOTIFICATIONS
import com.sahilpc.notificationapp.ui.data.remote.dto.NotificationData
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*

class OneSignalServiceImpl : OneSignalService {
    val client = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    override suspend fun sendNotification(notification: NotificationData): Boolean {
        return try {
            client.post<String> {
                url(NOTIFICATIONS)
                contentType(ContentType.Application.Json)
                header("Authorization", "Basic $API_KEY")
                body = notification
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}