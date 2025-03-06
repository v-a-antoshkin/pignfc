package com.ioffeivan.testnfc.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data")
data class DataEntity(

    @ColumnInfo("_id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo("value_key")
    val valueKey: String,

    @ColumnInfo("value")
    val value: String,
)