package com.example.unitconverterapp.compose

import android.icu.text.DecimalFormat
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.unitconverterapp.data.Conversion

@Composable
fun TopScreen(
    list: List<Conversion>,
    modifier: Modifier = Modifier,
    save: (String, String) -> Unit
) {
    val selectedConversion: MutableState<Conversion?> = remember { mutableStateOf(null) }
    val inputText: MutableState<String> = remember { mutableStateOf("") }
    val message1 = remember { mutableStateOf("") }
    val message2 = remember { mutableStateOf("") }

    ConversionMenu(list) {
        selectedConversion.value = it
    }

    selectedConversion.value?.let {
        InputBlock(it, inputText) { input ->
            val result = input * it.multiplyBy
            val df = DecimalFormat("#.####")
            val roundedResult = df.format(result)
            message1.value = "$input ${it.convertFrom} is equal to"
            message2.value = "$roundedResult ${it.convertTo}"
            save(message1.value, message2.value)
        }

        if (message1.value.isNotEmpty()) {
            ResultBlock(message1.value, message2.value)
        }
    }
}