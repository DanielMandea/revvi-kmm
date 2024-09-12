package com.mtdtechnology.revvi

import kotlinx.serialization.Serializable

@Serializable
data class Vignette(
    val country: String,
    val available: Boolean
)