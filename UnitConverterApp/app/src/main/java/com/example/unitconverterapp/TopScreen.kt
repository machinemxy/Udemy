package com.example.unitconverterapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun TopScreen(list: List<Conversion>, modifier: Modifier = Modifier) {
    val selectedConversion: MutableState<Conversion?> = remember { mutableStateOf(null) }
    val inputText: MutableState<String> = remember { mutableStateOf("") }

    ConversionMenu(list) {
        selectedConversion.value = it
    }

    selectedConversion.value?.let {
        InputBlock(it, inputText)
    }
}