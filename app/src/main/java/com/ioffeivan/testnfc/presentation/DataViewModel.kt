package com.ioffeivan.testnfc.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ioffeivan.testnfc.domain.model.Data
import com.ioffeivan.testnfc.domain.usecase.AddDataUseCase
import com.ioffeivan.testnfc.domain.usecase.GetDataByKeyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DataViewModel @Inject constructor(
    private val getDataByKeyUseCase: GetDataByKeyUseCase,
    private val addDataUseCase: AddDataUseCase,
) : ViewModel() {

    private val _data = MutableLiveData<Data>()
    val data: LiveData<Data> = _data

    init {
        viewModelScope.launch(Dispatchers.IO) {
            addDataUseCase(
                Data(
                    key = "key12345",
                    value = "value"
                )
            )
        }
    }

    fun getDataByKey(key: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = getDataByKeyUseCase(key)
            withContext(Dispatchers.Main) {
                _data.value = data
            }
        }
    }
}