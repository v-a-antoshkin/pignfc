package com.ioffeivan.testnfc.read_data.domain.usecase

import com.ioffeivan.testnfc.read_data.domain.repository.DataRepository
import javax.inject.Inject

class GetDataByKeyUseCase @Inject constructor(
    private val dataRepository: DataRepository
) {
    suspend operator fun invoke(key: String) = dataRepository.getDataByKey(key)
}