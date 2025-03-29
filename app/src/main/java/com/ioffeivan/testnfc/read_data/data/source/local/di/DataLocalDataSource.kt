package com.ioffeivan.testnfc.read_data.data.source.local.di

import com.ioffeivan.testnfc.core.data.source.local.db.AppDatabase
import com.ioffeivan.testnfc.read_data.data.repository.DataRepositoryImpl
import com.ioffeivan.testnfc.read_data.data.source.local.dao.DataDao
import com.ioffeivan.testnfc.read_data.domain.repository.DataRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataLocalDataSourceProvider {

    @Singleton
    @Provides
    fun provideDataDao(appDatabase: AppDatabase): DataDao {
        return appDatabase.dataDao()
    }
}

@InstallIn(SingletonComponent::class)
@Module
interface DataLocalDataSourceBinder {

    @Binds
    fun bindDataRepositoryImpl(dataRepositoryImpl: DataRepositoryImpl): DataRepository
}