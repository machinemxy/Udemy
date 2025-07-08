package com.example.unitconverterapp

import android.icu.text.DecimalFormat
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import java.math.RoundingMode

@Composable
fun TopScreen(list: List<Conversion>, modifier: Modifier = Modifier) {
    val selectedConversion: MutableState<Conversion?> = remember { mutableStateOf(null) }
    val inputText: MutableState<String> = remember { mutableStateOf("") }
    val typedValue = remember { mutableDoubleStateOf(0.0) }

    ConversionMenu(list) {
        selectedConversion.value = it
    }

    selectedConversion.value?.let {
        InputBlock(it, inputText) { input ->
            typedValue.doubleValue = input
        }

        if (typedValue.doubleValue != 0.0) {
            val result = typedValue.doubleValue * it.multiplyBy
            val df = DecimalFormat("#.####")
            val roundedResult = df.format(result)
            val message1 = "${typedValue.doubleValue} ${it.convertFrom} is equal to"
            val message2 = "$roundedResult ${it.convertTo}"
            ResultBlock(message1, message2)
        }
    }
}