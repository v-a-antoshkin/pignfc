package com.ioffeivan.testnfc.data.mapper

import com.ioffeivan.testnfc.data.source.local.model.DataEntity
import com.ioffeivan.testnfc.domain.model.Data

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