package com.example.locationtracking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.locationtracking.ui.theme.LocationTrackingTheme
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : ComponentActivity() {

    private val ioScope = CoroutineScope(SupervisorJob()+Dispatchers.IO)
    private lateinit var locationClient: LocationClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationClient=LocationProviderClient(this,LocationServices.getFusedLocationProviderClient(this))
        setContent {
               LocationTrackingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(locationClient,ioScope)
                }
            }
        }
    }
}

@Composable
fun MainScreen(locationClient: LocationClient, ioScope: CoroutineScope) {
    var text by remember { mutableStateOf("Current Location") }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(10.dp))

        Text(text)
        
        Spacer(modifier = Modifier.height(5.dp))
        
        Button(onClick = {
            locationClient.getLocation(2000L)
                .catch { e -> e.printStackTrace() }
                .onEach {
                    println("location - ${it.latitude}/${it.longitude}")
                    text = "CurrentLocation : ${it.latitude}/${it.longitude}"
                }
                .launchIn(ioScope)
        }) {
            Text(text = "Fetch Location")
        }
        
    }
}



