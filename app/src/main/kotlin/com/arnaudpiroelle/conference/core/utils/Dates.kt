package com.arnaudpiroelle.conference.core.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


object Dates {
    private val utcDateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

    init {
        utcDateFormat.timeZone = TimeZone.getTimeZone("UTC")
    }

    fun fromUtc(date: String): Date {
        return utcDateFormat.parse(date)
    }

}