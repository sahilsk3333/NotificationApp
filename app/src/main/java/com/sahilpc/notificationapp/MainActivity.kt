package com.sahilpc.notificationapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sahilpc.notificationapp.data.common.Constants
import com.sahilpc.notificationapp.data.remote.OneSignalServiceImpl
import com.sahilpc.notificationapp.data.remote.dto.NotificationData
import com.sahilpc.notificationapp.data.remote.dto.NotificationMessage
import com.sahilpc.notificationapp.ui.theme.NotificationAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val service = OneSignalServiceImpl()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            NotificationAppTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    val scope = rememberCoroutineScope()
                    var title by remember {
                        mutableStateOf("")
                    }
                    var description by remember {
                        mutableStateOf("")
                    }
                    TextField(
                        value = title,
                        onValueChange = { title = it },
                        placeholder = {
                            Text("Title")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = description,
                        onValueChange = { description = it },
                        placeholder = {
                            Text("Description")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            scope.launch {
                               val status = service.sendNotification(
                                    NotificationData(
                                        includedSegments = listOf("All"),
                                        headings = NotificationMessage(en = title),
                                        contents = NotificationMessage(en = description),
                                        appId = Constants.ONESIGNAL_APP_ID
                                    )
                                )
                                if (status){
                                    Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
                                }
                            }
                        },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text = "Send")
                    }
                }
            }


        }
    }
}
