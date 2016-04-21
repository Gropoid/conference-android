package com.arnaudpiroelle.conference.model

data class Speaker(val id: String? = null, val name: String? = null, val bio: String? = null, val company: String? = null, val thumbnailUrl: String? = null, val twitter: String?, val github: String?, val website: String?) {

    companion object {
        const val TABLE_NAME: String = "speakers"

        const val COL_ID: String = "id";
        const val COL_NAME: String = "name";
        const val COL_BIO: String = "bio";
        const val COL_COMPANY: String = "company";
        const val COL_THUMBNAIL: String = "thumbnailUrl";
        const val COL_TWITTER: String = "twitter";
        const val COL_GITHUB: String = "github";
        const val COL_WEBSITE: String = "website";
    }

}