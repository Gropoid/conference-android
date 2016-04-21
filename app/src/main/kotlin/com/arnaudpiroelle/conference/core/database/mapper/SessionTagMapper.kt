package com.arnaudpiroelle.conference.core.database.mapper

import android.content.ContentValues
import android.database.Cursor
import com.arnaudpiroelle.conference.core.database.utils.getString
import com.arnaudpiroelle.conference.model.rel.SessionTag


object SessionTagMapper {
    val toSessionTag: (Cursor) -> SessionTag = {
        val sessionId = it.getString(SessionTag.COL_SESSION)
        val tagId = it.getString(SessionTag.COL_TAG)

        SessionTag(sessionId!!, tagId!!)
    }

    fun toContentValues(sessionTag: SessionTag): ContentValues {
        val contentValues = ContentValues()

        contentValues.put(SessionTag.COL_SESSION, sessionTag.sessionId)
        contentValues.put(SessionTag.COL_TAG, sessionTag.tagId)

        return contentValues
    }
}