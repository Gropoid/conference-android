package com.arnaudpiroelle.conference.core.utils

import android.content.Context
import android.text.format.DateUtils
import com.arnaudpiroelle.conference.BuildConfig
import com.arnaudpiroelle.conference.R
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

    fun formatSessionPeriod(context: Context, start: Date, end: Date): String {

        val timezone = SettingsUtils.getDisplayTimeZone(context)
        val conferenceStart = fromUtc(BuildConfig.CONFERENCE_START)
        val conferenceEnd = fromUtc(BuildConfig.CONFERENCE_END)

        val singleDayConference = isSameDayDisplay(context, conferenceStart, conferenceEnd)
        val currentTimeMillis = System.currentTimeMillis()
        val conferenceEnded = currentTimeMillis > conferenceEnd.time
        val sessionEnded = currentTimeMillis > end.time

        if (sessionEnded && !conferenceEnded) {
            return context.getString(R.string.session_finished)
        }

        var flags = DateUtils.FORMAT_SHOW_TIME

        if (!singleDayConference) {
            flags = flags or DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_ABBREV_ALL or DateUtils.FORMAT_NO_YEAR
        }

        return DateUtils.formatDateRange(context, Formatter(StringBuilder()), start.time, end.time, flags, timezone.id).toString()
    }

    fun isSameDayDisplay(context: Context, time1: Date, time2: Date): Boolean {
        val displayTimeZone = SettingsUtils.getDisplayTimeZone(context)

        val cal1 = Calendar.getInstance(displayTimeZone)
        val cal2 = Calendar.getInstance(displayTimeZone)

        cal1.time = time1
        cal2.time = time2

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
    }

}