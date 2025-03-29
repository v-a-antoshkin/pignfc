package com.ioffeivan.testnfc.read_data.data.repository

import com.ioffeivan.testnfc.read_data.data.mapper.toData
import com.ioffeivan.testnfc.read_data.data.mapper.toDataEntity
import com.ioffeivan.testnfc.read_data.data.source.local.dao.DataDao
import com.ioffeivan.testnfc.read_data.domain.model.Data
import com.ioffeivan.testnfc.read_data.domain.repository.DataRepository
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor (
    private val dataDao: DataDao
): DataRepository {

    override suspend fun getDataByKey(key: String): Data {
        return dataDao.getDataByKey(key).toData()
    }

    override suspend fun addData(data: Data) {
        dataDao.addData(data.toDataEntity())
    }
}