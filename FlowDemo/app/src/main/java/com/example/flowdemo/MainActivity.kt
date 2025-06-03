package com.example.flowdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.flowdemo.ui.theme.FlowDemoTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val myFlow = flow<Int> {
            for (i in 1..100) {
                emit(i)
                delay(1000L)
            }
        }

        setContent {
            val currentValue = myFlow.collectAsState(initial = 1).value
            FlowDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Text("Hello $currentValue", modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
