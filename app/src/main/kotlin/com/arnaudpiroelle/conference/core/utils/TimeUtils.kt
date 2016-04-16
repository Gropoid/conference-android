package com.arnaudpiroelle.conference.core.utils

import android.content.Context
import android.text.format.DateUtils
import java.util.*


object TimeUtils {
    fun formatShortDate(context: Context, date: Date): String {
        val sb = StringBuilder()
        val formatter = Formatter(sb)
        return DateUtils.formatDateRange(context, formatter, date.time, date.time,
                DateUtils.FORMAT_ABBREV_ALL or DateUtils.FORMAT_NO_YEAR,
                SettingsUtils.getDisplayTimeZone(context).getID()).toString()
    }

    fun formatShortTime(context: Context, time: Date): String {
        val format = android.text.format.DateFormat.getTimeFormat(context)
        val tz = SettingsUtils.getDisplayTimeZone(context)

        format.timeZone = tz

        return format.format(time)
    }
}