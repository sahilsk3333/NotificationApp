package com.sahilpc.notificationapp.ui.data.remote

import com.sahilpc.notificationapp.ui.data.remote.dto.NotificationData

interface OneSignalService {

    suspend fun sendNotification(notification: NotificationData): Boolean

}