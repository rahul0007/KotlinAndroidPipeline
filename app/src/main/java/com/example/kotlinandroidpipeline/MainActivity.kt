package com.example.kotlinandroidpipeline

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.kotlinandroidpipeline.ui.theme.KotlinAndroidPipelineTheme
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCenter.start(
            getApplication(), "1438a942-d07b-42c2-a9f9-36b4dc229821",
            Analytics::class.java, Crashes::class.java

        )
        enableEdgeToEdge()
        setContent {
            KotlinAndroidPipelineTheme {

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { paddingValues ->

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        val context = LocalContext.current
// Test
                        var text by remember {
                            mutableStateOf("")
                        }

                        OutlinedTextField(
                            value = text,
                            onValueChange = {
                                text = it
                            },
                            label = {
                                Text("Enter text")
                            }
                        )

                        Button(
                            onClick = {
                                Toast.makeText(
                                    context,
                                    text,
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            modifier = Modifier.padding(top = 16.dp)
                        ) {
                            Text("Show Toast")
                        }

                        /*Button(
                            onClick = {
                                Analytics.trackEvent("Button Clicked", mapOf("Text" to text))
                            },
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Text("Track Event")
                        }*/

                        Button(
                            onClick = {
                                Analytics.trackEvent("My custom event");
                                throw RuntimeException("Test Crash from App Center")
                            },
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Text("Test Crash")
                        }
                    }
                }
            }
        }
    }
}