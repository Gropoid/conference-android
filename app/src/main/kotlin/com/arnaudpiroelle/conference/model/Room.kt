package com.arnaudpiroelle.conference.model

data class Room(val id: String? = null, val name: String? = null) {

    companion object {
        const val TABLE_NAME: String = "rooms"

        const val COL_ID: String = "_id";
        const val COL_NAME: String = "name";
    }
}
