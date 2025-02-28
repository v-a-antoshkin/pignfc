package com.ioffeivan.testnfc.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data")
data class DataEntity(
    @PrimaryKey
    val valueKey: String,
    val value: String
)