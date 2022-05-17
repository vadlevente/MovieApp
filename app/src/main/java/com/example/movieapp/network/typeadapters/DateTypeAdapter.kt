package com.example.movieapp.network.typeadapters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

class DateTypeAdapter: JsonDeserializer<Date?> {

    private val dateFormat = "yyyy-MM-dd"

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Date? {
        if (json == null) return null
        val dateString = json.asString
        return try {
            SimpleDateFormat(dateFormat).parse(dateString)
        } catch (e: Exception){
            null
        }
    }
}