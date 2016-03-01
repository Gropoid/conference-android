package com.arnaudpiroelle.conference.model

import java.util.*

class Session {

    var id: String? = null
    var title: String? = null
    var description: String? = null
    var tags: List<Tag>? = null
    var mainTag: Tag? = null
    var startTimestamp: Date? = null
    var endTimestamp: Date? = null
    var photoUrl: String? = null
    var speakers: List<Speaker>? = null
    var room: Room? = null
}
