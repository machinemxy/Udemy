package com.example.unitconverterapp.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.example.unitconverterapp.data.ConversionResult
import androidx.compose.ui.Modifier

@Composable
fun HistoryList(
    list: State<List<ConversionResult>>,
    onCloseTask: (ConversionResult) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(
            items = list.value,
            key = { item -> item.id }
        ) { item ->
            HistoryItem(item.message1, item.message2, { onCloseTask(item) })
        }
    }
}