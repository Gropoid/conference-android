package com.arnaudpiroelle.conference.core.api.response

import java.util.*

data class BlockResponse(var id: String? = null, var title: String? = null, var subtitle: String? = null, var type: String? = null, var start: Date? = null, var end: Date? = null)
data class RoomResponse(var id: String? = null, var name: String? = null)
data class SpeakerResponse(var id: String? = null, var name: String? = null, var bio: String? = null, var company: String? = null, var thumbnailUrl: String? = null, var twitter: String?, var github: String?, var website: String?)
data class TagResponse(var id: String? = null, var name: String? = null, var type: String? = null)
data class VideoResponse(var id: String? = null, var title: String? = null, var description: String? = null, var thumbnailUrl: String? = null, var topic: String? = null, var speakers: String? = null)
data class SessionResponse(var id: String? = null, var title: String? = null, var description: String? = null, var tags: List<String>? = null, var mainTag: String? = null, var start: Date? = null, var end: Date? = null, var photoUrl: String? = null, var speakers: List<String>? = null, var room: String? = null)