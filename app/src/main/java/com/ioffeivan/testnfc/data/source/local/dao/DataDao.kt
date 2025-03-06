package com.ioffeivan.testnfc.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ioffeivan.testnfc.data.source.local.model.DataEntity

@Dao
interface DataDao {

    @Query("SELECT * FROM data WHERE value_key = :key")
    suspend fun getDataByKey(key: String): DataEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addData(dataEntity: DataEntity)
}