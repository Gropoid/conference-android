package com.arnaudpiroelle.conference.core.database.mapper

import android.content.ContentValues
import android.database.Cursor
import com.arnaudpiroelle.conference.core.database.utils.getString
import com.arnaudpiroelle.conference.model.Room
import com.arnaudpiroelle.conference.model.Room.Companion.COL_ID
import com.arnaudpiroelle.conference.model.Room.Companion.COL_NAME


object RoomMapper {
    val toRoom: (Cursor) -> Room = {
        val id = it.getString(COL_ID)
        val name = it.getString(COL_NAME)

        Room(id, name)
    }

    fun toContentValues(room: Room): ContentValues {
        val contentValues = ContentValues()

        contentValues.put(COL_ID, room.id)
        contentValues.put(COL_NAME, room.name)

        return contentValues
    }
}