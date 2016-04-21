package com.arnaudpiroelle.conference.model

import java.util.*

data class Block(val id: String? = null, val title: String? = null, val subtitle: String? = null, val type: String? = null, val start: Date? = null, val end: Date? = null) {

    companion object {
        const val TABLE_NAME = "blocks"

        const val COL_ID = "_id"
        const val COL_TITLE = "title"
        const val COL_SUBTITLE = "subtitle"
        const val COL_TYPE = "type"
        const val COL_START = "start"
        const val COL_END = "end"
    }

}