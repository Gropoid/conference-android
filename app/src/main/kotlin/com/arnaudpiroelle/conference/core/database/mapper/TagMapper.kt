package com.arnaudpiroelle.conference.core.database.mapper

import android.content.ContentValues
import android.database.Cursor
import com.arnaudpiroelle.conference.core.database.utils.getString
import com.arnaudpiroelle.conference.model.Tag
import com.arnaudpiroelle.conference.model.Tag.Companion.COL_ID
import com.arnaudpiroelle.conference.model.Tag.Companion.COL_NAME
import com.arnaudpiroelle.conference.model.Tag.Companion.COL_TYPE

object TagMapper {
    val toTag: (Cursor) -> Tag = {
        val id = it.getString(COL_ID)
        val name = it.getString(COL_NAME)
        val type = it.getString(COL_TYPE)

        Tag(id, name, type)
    }

    fun toContentValues(tag: Tag): ContentValues {
        val contentValues = ContentValues()

        contentValues.put(COL_ID, tag.id)
        contentValues.put(COL_NAME, tag.name)
        contentValues.put(COL_TYPE, tag.type)

        return contentValues
    }
}