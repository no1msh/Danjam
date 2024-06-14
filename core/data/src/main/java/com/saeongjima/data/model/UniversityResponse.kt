package com.saeongjima.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UniversityResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("korName")
    val korName: String
)
