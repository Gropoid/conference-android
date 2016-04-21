package com.arnaudpiroelle.conference.core.database.mapper

import android.content.ContentValues
import android.database.Cursor
import com.arnaudpiroelle.conference.core.database.utils.getString
import com.arnaudpiroelle.conference.model.rel.SessionSpeaker


object SessionSpeakerMapper {

    val toSessionSpeaker: (Cursor) -> SessionSpeaker = {
        val sessionId = it.getString(SessionSpeaker.COL_SESSION)
        val speakerId = it.getString(SessionSpeaker.COL_SPEAKER)

        SessionSpeaker(sessionId!!, speakerId!!)
    }

    fun toContentValues(sessionSpeaker: SessionSpeaker): ContentValues {
        val contentValues = ContentValues()

        contentValues.put(SessionSpeaker.COL_SESSION, sessionSpeaker.sessionId)
        contentValues.put(SessionSpeaker.COL_SPEAKER, sessionSpeaker.speakerId)

        return contentValues
    }

}