package com.arnaudpiroelle.conference.model

data class Video(var id: String? = null, var title: String? = null, var description: String? = null, var thumbnailUrl: String? = null, var topic: String? = null, var speakers: String? = null) {

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