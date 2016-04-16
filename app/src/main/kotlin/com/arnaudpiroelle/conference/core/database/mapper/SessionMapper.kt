package com.arnaudpiroelle.conference.core.database.mapper

import android.content.ContentValues
import android.database.Cursor
import android.text.TextUtils
import com.arnaudpiroelle.conference.core.database.utils.getLong
import com.arnaudpiroelle.conference.core.database.utils.getString
import com.arnaudpiroelle.conference.model.Session
import com.arnaudpiroelle.conference.model.Session.Companion.COL_DESCRIPTION
import com.arnaudpiroelle.conference.model.Session.Companion.COL_END
import com.arnaudpiroelle.conference.model.Session.Companion.COL_ID
import com.arnaudpiroelle.conference.model.Session.Companion.COL_MAIN_TAG
import com.arnaudpiroelle.conference.model.Session.Companion.COL_PHOTO_URL
import com.arnaudpiroelle.conference.model.Session.Companion.COL_ROOM
import com.arnaudpiroelle.conference.model.Session.Companion.COL_SPEAKERS
import com.arnaudpiroelle.conference.model.Session.Companion.COL_START
import com.arnaudpiroelle.conference.model.Session.Companion.COL_TAGS
import com.arnaudpiroelle.conference.model.Session.Companion.COL_TITLE
import java.util.*


object SessionMapper {
    val toSession: (Cursor) -> Session = {
        val id = it.getString(COL_ID)
        val title = it.getString(COL_TITLE)
        val description = it.getString(COL_DESCRIPTION)
        val tags = it.getString(COL_TAGS)?.split(",")
        val mainTag = it.getString(COL_MAIN_TAG)
        val start = it.getLong(COL_START)
        val end = it.getLong(COL_END)
        val photoUrl = it.getString(COL_PHOTO_URL)
        val speakers = it.getString(COL_SPEAKERS)?.split(",")
        val room = it.getString(COL_ROOM)

        Session(id, title, description, tags, mainTag, Date(start!!), Date(end!!), photoUrl, speakers, room)
    }

    fun toContentValues(session: Session): ContentValues {
        val contentValues = ContentValues()

        contentValues.put(COL_ID, session.id)
        contentValues.put(COL_TITLE, session.title)
        contentValues.put(COL_DESCRIPTION, session.description)
        contentValues.put(COL_START, "${session.start?.time}")
        contentValues.put(COL_END, "${session.end?.time}")
        contentValues.put(COL_MAIN_TAG, session.mainTag)
        contentValues.put(COL_PHOTO_URL, session.photoUrl)
        contentValues.put(COL_ROOM, session.room)
        contentValues.put(COL_SPEAKERS, if (session.speakers != null) TextUtils.join(",", session.speakers) else null)
        contentValues.put(COL_TAGS, if (session.tags != null) TextUtils.join(",", session.tags) else null)

        return contentValues
    }
}