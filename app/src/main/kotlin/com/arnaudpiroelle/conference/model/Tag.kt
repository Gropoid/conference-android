package com.arnaudpiroelle.conference.model

data class Tag(val id: String? = null, val name: String? = null, val type: String? = null) {

    companion object {
        const val TABLE_NAME: String = "tags"

        const val COL_ID: String = "id";
        const val COL_NAME: String = "name";
        const val COL_TYPE: String = "type";
    }
}
