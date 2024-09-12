package com.mtdtechnology.revvi

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class Car(
    val id: String,
    val name: String,
    val numberPlate: String,
    val vin: String,
    val engine: String,
    val hp: Int,
    var lastService: String? = null,
    var vignette: Vignette? = null
)
