package com.example.snackbardemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.snackbardemo.ui.theme.SnackBarDemoTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SnackBarDemoTheme {
                DisplaySnackBar()
            }
        }
    }
}

@Composable
fun DisplaySnackBar() {
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { innerPadding ->
        Button(onClick = {
            coroutineScope.launch {
                val snackBarResult = snackBarHostState.showSnackbar(
                    message = "This is the message",
                    actionLabel = "Undo",
                    duration = SnackbarDuration.Short
                )
                when(snackBarResult) {
                    SnackbarResult.Dismissed -> Log.i("MYTAG", "Dismissed")
                    SnackbarResult.ActionPerformed -> Log.i("MYTAG", "ActionPerformed")
                }
            }
        }, modifier = Modifier.padding(innerPadding)) {
            Text(text = "Display Snack Bar")
        }
    }
}