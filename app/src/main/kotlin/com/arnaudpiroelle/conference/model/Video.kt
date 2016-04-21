package com.arnaudpiroelle.conference.model

data class Video(val id: String? = null, val title: String? = null, val description: String? = null, val thumbnailUrl: String? = null, val topic: String? = null, val speakers: String? = null) {

    companion object {
        const val TABLE_NAME: String = "videos"

        const val COL_ID: String = "id";
        const val COL_TITLE: String = "title";
        const val COL_DESCRIPTION: String = "description";
        const val COL_THUMBNAIL: String = "thumbnailUrl";
        const val COL_TOPIC: String = "topic";
        const val COL_SPEAKERS: String = "speakers";
    }

}