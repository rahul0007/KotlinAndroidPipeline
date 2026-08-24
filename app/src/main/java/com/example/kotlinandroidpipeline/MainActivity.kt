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

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                    }
                }
            }
        }
    }
}