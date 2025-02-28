package com.ioffeivan.testnfc.domain.usecase

import com.ioffeivan.testnfc.domain.model.Data
import com.ioffeivan.testnfc.domain.repository.DataRepository
import javax.inject.Inject

class AddDataUseCase @Inject constructor(
    private val dataRepository: DataRepository
) {
    suspend operator fun invoke(data: Data) = dataRepository.addData(data)
}