package com.ioffeivan.testnfc.read_data.data.mapper

import com.ioffeivan.testnfc.read_data.data.source.local.model.DataEntity
import com.ioffeivan.testnfc.read_data.domain.model.Data

fun DataEntity.toData() =
    Data(
        key = valueKey,
        value = value,
    )

fun Data.toDataEntity() =
    DataEntity(
        valueKey = key,
        value = value,
    )