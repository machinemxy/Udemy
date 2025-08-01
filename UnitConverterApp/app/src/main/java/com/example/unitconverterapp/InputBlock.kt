package com.example.unitconverterapp

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputBlock(
    conversion: Conversion,
    inputText: MutableState<String>,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    calculate: (Double) -> Unit
) {
    Column(modifier = modifier.padding(0.dp, 20.dp, 0.dp, 0.dp)) {
        Row(modifier = modifier.fillMaxWidth()) {
            TextField(
                value = inputText.value,
                onValueChange = {
                    inputText.value = it
                },
                modifier = modifier.fillMaxWidth(0.65f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                textStyle = TextStyle(fontSize = 30.sp),
            )

            Text(
                text = conversion.convertFrom,
                fontSize = 24.sp,
                modifier = modifier.padding(10.dp, 30.dp, 0.dp, 0.dp)
                    .fillMaxWidth(0.35f)
            )
        }

        Spacer(modifier = modifier.height(20.dp))

        OutlinedButton(
            onClick = {
                val doubleValue = inputText.value.toDoubleOrNull()
                if (doubleValue != null) {
                    calculate(doubleValue)
                } else {
                    Toast.makeText(context, "Please enter correct value", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = "Convert",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
