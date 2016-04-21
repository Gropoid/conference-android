package com.arnaudpiroelle.conference.model

import java.util.*

data class Session(val id: String? = null, val title: String? = null, val description: String? = null, val mainTag: String? = null, val start: Date? = null, val end: Date? = null, val photoUrl: String? = null, val room: String? = null) {

    companion object {
        const val TABLE_NAME: String = "sessions"

        const val COL_ID: String = "id";
        const val COL_TITLE: String = "title";
        const val COL_DESCRIPTION: String = "description";
        const val COL_MAIN_TAG: String = "mainTag";
        const val COL_START: String = "start";
        const val COL_END: String = "end";
        const val COL_PHOTO_URL: String = "photoUrl";
        const val COL_ROOM: String = "room";

    }

}
