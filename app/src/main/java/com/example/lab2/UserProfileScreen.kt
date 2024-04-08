package com.example.lab2

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(navController: NavController) {
    val userName = remember { mutableStateOf("Ім'я Користувача") }
    val userEmail = remember { mutableStateOf("email@example.com") }
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> Log.d("LifecycleDemo", "ON_CREATE")
                Lifecycle.Event.ON_START -> Log.d("LifecycleDemo", "ON_START")
                Lifecycle.Event.ON_RESUME -> Log.d("LifecycleDemo", "ON_RESUME")
                Lifecycle.Event.ON_PAUSE -> Log.d("LifecycleDemo", "ON_PAUSE")
                Lifecycle.Event.ON_STOP -> Log.d("LifecycleDemo", "ON_STOP")
                Lifecycle.Event.ON_DESTROY -> Log.d("LifecycleDemo", "ON_DESTROY")
                else -> throw IllegalStateException("Unexpected lifecycle event: $event")
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Профіль") },
                actions = {
                    IconButton(onClick = { navController.navigate("bookList")}) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "На головну")
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier=Modifier.padding(padding)) {
            Column(modifier = Modifier.padding(16.dp, 0.dp)) {
                TextField(
                    value = userName.value,
                    onValueChange = { userName.value = it },
                    label = { Text("Ім'я") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = userEmail.value,
                    onValueChange = { userEmail.value = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(onClick = { navController.navigate("bookList") }) {
                    Text("Зберегти зміни")
                }
            }
        }
    }
}
