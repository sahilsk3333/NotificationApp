package com.sahilpc.notificationapp.data.remote

import com.sahilpc.notificationapp.data.remote.dto.NotificationData

interface OneSignalService {

    suspend fun sendNotification(notification: NotificationData): Boolean

}