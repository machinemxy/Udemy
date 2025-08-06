package com.example.unitconverterapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ConversionResult(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val message1: String,
    val message2: String
)
