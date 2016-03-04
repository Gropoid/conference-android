package com.arnaudpiroelle.conference.model

import java.util.*

data class Block(var id: String? = null, var title: String? = null, var subtitle: String? = null, var type: String? = null, var start: Date? = null, var end: Date? = null) {

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