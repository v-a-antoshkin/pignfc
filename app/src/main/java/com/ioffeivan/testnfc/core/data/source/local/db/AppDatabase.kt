package com.ioffeivan.testnfc.core.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ioffeivan.testnfc.data.source.local.dao.DataDao
import com.ioffeivan.testnfc.data.source.local.model.DataEntity

@Database(entities = [DataEntity::class], version = 4)
abstract class AppDatabase: RoomDatabase() {
    abstract fun dataDao(): DataDao
}