package com.mtdtechnology.revvi.models

import com.mtdtechnology.revvi.Car
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString

import org.bson.Document
import org.reactivestreams.Publisher

object CarDocumentMapper {
    val json = Json { ignoreUnknownKeys = true }

    suspend fun fromDocument(publisher: Publisher<Document>): Car? {
        // Wait for the first document to be emitted by the Publisher
        val document = publisher.awaitFirstOrNull()

        // If document is not null, convert it to JSON string and then to Car
        return document?.let {
            json.decodeFromString<Car>(it.toJson())
        }
    }
}