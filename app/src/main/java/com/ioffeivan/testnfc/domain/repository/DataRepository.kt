package com.ioffeivan.testnfc.domain.repository

import com.ioffeivan.testnfc.domain.model.Data

interface DataRepository {
    suspend fun getDataByKey(key: String): Data
    suspend fun addData(data: Data)
}