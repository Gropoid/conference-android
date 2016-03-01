package com.arnaudpiroelle.conference.core.json

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException

import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

class DateDeserializer : JsonDeserializer<Date> {
    internal var dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'")

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Date? {
        try {
            return dateFormat.parse(json.asString)
        } catch (e: ParseException) {
            return null
        }

    }
}
