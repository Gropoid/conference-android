package com.arnaudpiroelle.conference.core.json

import com.arnaudpiroelle.conference.core.utils.Dates
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type
import java.text.ParseException
import java.util.*

class DateDeserializer : JsonDeserializer<Date> {
    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Date? {
        try {
            return Dates.fromUtc(json.asString)
        } catch (e: ParseException) {
            return null
        }

    }
}
