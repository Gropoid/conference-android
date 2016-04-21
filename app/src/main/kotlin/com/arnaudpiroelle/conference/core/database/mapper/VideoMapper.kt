package com.arnaudpiroelle.conference.core.database.mapper

import android.content.ContentValues
import android.database.Cursor
import com.arnaudpiroelle.conference.core.database.utils.getString
import com.arnaudpiroelle.conference.model.Video
import com.arnaudpiroelle.conference.model.Video.Companion.COL_DESCRIPTION
import com.arnaudpiroelle.conference.model.Video.Companion.COL_ID
import com.arnaudpiroelle.conference.model.Video.Companion.COL_SPEAKERS
import com.arnaudpiroelle.conference.model.Video.Companion.COL_THUMBNAIL
import com.arnaudpiroelle.conference.model.Video.Companion.COL_TITLE
import com.arnaudpiroelle.conference.model.Video.Companion.COL_TOPIC


object VideoMapper {
    val toVideo: (Cursor) -> Video = {
        val id = it.getString(COL_ID)
        val title = it.getString(COL_TITLE)
        val description = it.getString(COL_DESCRIPTION)
        val thumbnailUrl = it.getString(COL_THUMBNAIL)
        val topic = it.getString(COL_TOPIC)
        val speakers = it.getString(COL_SPEAKERS)

        Video(id, title, description, thumbnailUrl, topic, speakers)
    }

    fun toContentValues(video: Video): ContentValues {
        val contentValues = ContentValues()

        contentValues.put(COL_ID, video.id)
        contentValues.put(COL_TITLE, video.title)
        contentValues.put(COL_DESCRIPTION, video.description)
        contentValues.put(COL_SPEAKERS, video.speakers)
        contentValues.put(COL_THUMBNAIL, video.thumbnailUrl)
        contentValues.put(COL_TOPIC, video.topic)

        return contentValues
    }
}