package com.mtdtechnology.revvi.models

import com.mtdtechnology.revvi.Car
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bson.Document

fun Car.toDocument(): Document {
    return Document.parse(Json.encodeToString(this))
}
