package com.mtdtechnology.revvi.service

import com.mongodb.client.model.Filters
import com.mongodb.reactivestreams.client.*
import com.mtdtechnology.revvi.Car
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bson.Document
import org.bson.types.ObjectId
import com.mtdtechnology.revvi.models.*

class CarService(private val database: MongoDatabase) {
    var collection: MongoCollection<Document>

    init {
        database.createCollection("cars")
        collection = database.getCollection("cars")
    }

    // Create new car
    suspend fun create(car: Car): String = withContext(Dispatchers.IO) {
        val doc = car.toDocument()
        collection.insertOne(doc)
        doc["_id"].toString()
    }

    // Read a car
    suspend fun read(id: String): Car? = withContext(Dispatchers.IO) {
        collection.find(Filters.eq("_id", ObjectId(id))).first()?.let {
            CarDocumentMapper.fromDocument(it)
        }
    }


    // Update a car
    suspend fun update(id: String, car: Car): Car? = withContext(Dispatchers.IO) {
        collection.findOneAndReplace(Filters.eq("_id", ObjectId(id)), car.toDocument())?.let {
            CarDocumentMapper.fromDocument(it)
        }
    }

    // Delete a car
    suspend fun delete(id: String): Car? = withContext(Dispatchers.IO) {
        collection.findOneAndDelete(Filters.eq("_id", ObjectId(id)))?.let {
            CarDocumentMapper.fromDocument(it)
        }
    }
}