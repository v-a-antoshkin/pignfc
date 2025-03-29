package com.ioffeivan.testnfc.read_data.domain.usecase

import com.ioffeivan.testnfc.read_data.domain.model.Data
import com.ioffeivan.testnfc.read_data.domain.repository.DataRepository
import javax.inject.Inject

class AddDataUseCase @Inject constructor(
    private val dataRepository: DataRepository
) {
    suspend operator fun invoke(data: Data) = dataRepository.addData(data)
}