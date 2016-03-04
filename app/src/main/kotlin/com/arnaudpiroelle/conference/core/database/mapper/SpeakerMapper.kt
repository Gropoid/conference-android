package com.arnaudpiroelle.conference.core.database.mapper

import android.content.ContentValues
import android.database.Cursor
import com.arnaudpiroelle.conference.core.database.utils.getString
import com.arnaudpiroelle.conference.model.Speaker
import com.arnaudpiroelle.conference.model.Speaker.Companion.COL_BIO
import com.arnaudpiroelle.conference.model.Speaker.Companion.COL_COMPANY
import com.arnaudpiroelle.conference.model.Speaker.Companion.COL_ID
import com.arnaudpiroelle.conference.model.Speaker.Companion.COL_NAME
import com.arnaudpiroelle.conference.model.Speaker.Companion.COL_THUMBNAIL


object SpeakerMapper {
    val toSpeaker: (Cursor) -> Speaker = {
        val id = it.getString(COL_ID)
        val name = it.getString(COL_NAME)
        val bio = it.getString(COL_BIO)
        val company = it.getString(COL_COMPANY)
        val thumbnailUrl = it.getString(COL_THUMBNAIL)

        Speaker(id, name, bio, company, thumbnailUrl)
    }

    fun toContentValues(speaker: Speaker): ContentValues {
        val contentValues = ContentValues()

        contentValues.put(COL_ID, speaker.id)
        contentValues.put(COL_NAME, speaker.name)
        contentValues.put(COL_BIO, speaker.bio)
        contentValues.put(COL_COMPANY, speaker.company)
        contentValues.put(COL_THUMBNAIL, speaker.thumbnailUrl)

        return contentValues
    }
}