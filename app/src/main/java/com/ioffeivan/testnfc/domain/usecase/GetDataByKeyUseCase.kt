package com.ioffeivan.testnfc.domain.usecase

import com.ioffeivan.testnfc.domain.repository.DataRepository
import javax.inject.Inject

class GetDataByKeyUseCase @Inject constructor(
    private val dataRepository: DataRepository
) {
    suspend operator fun invoke(key: String) = dataRepository.getDataByKey(key)
}