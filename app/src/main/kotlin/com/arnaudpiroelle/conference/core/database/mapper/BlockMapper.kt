package com.arnaudpiroelle.conference.core.database.mapper

import android.content.ContentValues
import android.database.Cursor
import com.arnaudpiroelle.conference.core.database.utils.getString
import com.arnaudpiroelle.conference.model.Block
import com.arnaudpiroelle.conference.model.Block.Companion.COL_END
import com.arnaudpiroelle.conference.model.Block.Companion.COL_ID
import com.arnaudpiroelle.conference.model.Block.Companion.COL_START
import com.arnaudpiroelle.conference.model.Block.Companion.COL_SUBTITLE
import com.arnaudpiroelle.conference.model.Block.Companion.COL_TITLE
import com.arnaudpiroelle.conference.model.Block.Companion.COL_TYPE
import java.util.*


object BlockMapper {

    val toBlock: (Cursor) -> Block = {
        val id = it.getString(COL_ID)
        val title = it.getString(COL_TITLE)
        val subtitle = it.getString(COL_SUBTITLE)
        val type = it.getString(COL_TYPE)
        val start = it.getString(COL_START)
        val end = it.getString(COL_END)

        Block(id, title, subtitle, type, Date(start), Date(end))
    }

    fun toContentValues(block: Block): ContentValues {
        val contentValues = ContentValues()

        contentValues.put(COL_ID, block.id)
        contentValues.put(COL_TITLE, block.title)
        contentValues.put(COL_SUBTITLE, block.subtitle)
        contentValues.put(COL_TYPE, block.type)
        contentValues.put(COL_START, "${block.start?.time}")
        contentValues.put(COL_END, "${block.end?.time}")

        return contentValues
    }

}