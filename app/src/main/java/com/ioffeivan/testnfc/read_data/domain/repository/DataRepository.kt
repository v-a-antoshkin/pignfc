package com.ioffeivan.testnfc.read_data.domain.repository

import com.ioffeivan.testnfc.read_data.domain.model.Data

interface DataRepository {
    suspend fun getDataByKey(key: String): Data
    suspend fun addData(data: Data)
}