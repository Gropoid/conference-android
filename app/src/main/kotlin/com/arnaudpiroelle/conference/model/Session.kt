package com.arnaudpiroelle.conference.model

import java.util.*

data class Session(var id: String? = null, var title: String? = null, var description: String? = null, var tags: List<String>? = null, var mainTag: String? = null, var start: Date? = null, var end: Date? = null, var photoUrl: String? = null, var speakers: List<String>? = null, var room: String? = null) {

    companion object {
        const val TABLE_NAME: String = "sessions"

        const val COL_ID: String = "id";
        const val COL_TITLE: String = "title";
        const val COL_DESCRIPTION: String = "description";
        const val COL_TAGS: String = "tags";
        const val COL_MAIN_TAG: String = "mainTag";
        const val COL_START: String = "start";
        const val COL_END: String = "end";
        const val COL_PHOTO_URL: String = "photoUrl";
        const val COL_SPEAKERS: String = "speakers";
        const val COL_ROOM: String = "room";

    }

}
