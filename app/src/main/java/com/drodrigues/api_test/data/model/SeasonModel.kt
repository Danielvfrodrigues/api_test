package com.drodrigues.api_test.data.model

import com.drodrigues.api_test.domain.entity.SeasonEntity
import com.google.gson.annotations.SerializedName

internal data class SeasonModel(

    @SerializedName("id")
    val id: String,
    @SerializedName("number")
    val number: String
)

internal fun toEntity(model: SeasonModel): SeasonEntity {
    return SeasonEntity(
        id = model.id,
        number = model.number
    )
}

internal fun toSeasonEntityList(initial: List<SeasonModel>): List<SeasonEntity> {
    return initial.map { toEntity(it) }
}
