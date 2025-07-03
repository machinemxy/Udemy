package com.example.unitconverterapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TopScreen(list: List<Conversion>, modifier: Modifier = Modifier) {
    ConversionMenu(list, modifier)
}