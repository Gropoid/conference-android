package com.arnaudpiroelle.conference.ui.explore.model


data class Group<T>(val data: T, val type: GroupType) {


    enum class GroupType {
        MESSAGE,
        KEYNOTE,
        TAG
    }

}
