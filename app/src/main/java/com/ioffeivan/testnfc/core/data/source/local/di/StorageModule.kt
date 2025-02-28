package com.ioffeivan.testnfc.core.data.source.local.di

import android.content.Context
import androidx.room.Room
import com.ioffeivan.testnfc.core.data.source.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class StorageModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "data_database.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}