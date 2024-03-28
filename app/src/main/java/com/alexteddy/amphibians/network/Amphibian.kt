package com.alexteddy.amphibians.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Amphibian(
    val id: String,
    val name: String,
    val description: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)