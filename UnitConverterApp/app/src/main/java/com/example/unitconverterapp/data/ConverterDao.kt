package com.example.unitconverterapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ConverterDao {
    @Insert
    suspend fun insert(result: ConversionResult)

    @Delete
    suspend fun delete(result: ConversionResult)

    @Query("DELETE FROM ConversionResult")
    suspend fun deleteAll()

    @Query("SELECT * FROM ConversionResult")
    fun getAll(): Flow<List<ConversionResult>>
}