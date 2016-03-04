package com.arnaudpiroelle.conference.model

data class Speaker(var id: String? = null, var name: String? = null, var bio: String? = null, var company: String? = null, var thumbnailUrl: String? = null) {

    companion object {
        const val TABLE_NAME: String = "speakers"

        const val COL_ID: String = "id";
        const val COL_NAME: String = "name";
        const val COL_BIO: String = "bio";
        const val COL_COMPANY: String = "company";
        const val COL_THUMBNAIL: String = "thumbnailUrl";
    }

}