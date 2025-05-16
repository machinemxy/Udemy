package com.example.composestatedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.composestatedemo.ui.theme.ComposeStateDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel by viewModels<MyViewModel>()
            ComposeStateDemoTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MyButton(viewModel.count) { viewModel.increaseCount() }
                }

            }
        }
    }
}

@Composable
fun MyButton(count: Int, updateCount: (Int) -> Unit) {
    val context = LocalContext.current
    Button(
        onClick = {
            updateCount(count)
        }
    ) {
        Text("Count is ${count}")
    }
}