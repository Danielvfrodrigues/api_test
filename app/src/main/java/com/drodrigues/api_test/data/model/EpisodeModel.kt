package com.drodrigues.api_test.data.model

import com.drodrigues.api_test.domain.entity.EpisodeEntity
import com.drodrigues.api_test.domain.entity.ImageEntity
import com.google.gson.annotations.SerializedName

internal data class EpisodeModel(

    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("number")
    val number: String,
    @SerializedName("season")
    val season: String,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("image")
    val imageModel: ImageModel = ImageModel()

)

internal fun toEntity(model: EpisodeModel): EpisodeEntity {
    return EpisodeEntity(
        id = model.id,
        name = model.name,
        number= model.number,
        season = model.season,
        imageEntity = ImageEntity(
            medium = model.imageModel.medium,
            original = model.imageModel.original
        ),
        summary = model.summary,
    )
}

internal fun toEntityList(initial: List<EpisodeModel>): List<EpisodeEntity> {
    return initial.map { toEntity(it) }
}
