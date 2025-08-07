package com.example.unitconverterapp.data

import kotlinx.coroutines.flow.Flow

class ConverterRepositoryImpl(private val dao: ConverterDao): ConverterRepository {
    override suspend fun insertResult(result: ConversionResult) {
        dao.insert(result)
    }

    override suspend fun deleteResult(result: ConversionResult) {
        dao.delete(result)
    }

    override suspend fun deleteAllResults() {
        dao.deleteAll()
    }

    override fun getSavedResults(): Flow<List<ConversionResult>> {
        return dao.getAll()
    }
}