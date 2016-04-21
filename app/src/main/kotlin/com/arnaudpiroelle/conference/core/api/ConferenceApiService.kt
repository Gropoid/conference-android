package com.arnaudpiroelle.conference.core.api

import com.arnaudpiroelle.conference.core.api.response.*
import retrofit2.http.GET
import rx.Observable


interface ConferenceApiService {
    @GET("blocks.json")
    fun loadBlocks(): Observable<List<BlockResponse>>

    @GET("rooms.json")
    fun loadRooms(): Observable<List<RoomResponse>>

    @GET("tags.json")
    fun loadTags(): Observable<List<TagResponse>>

    @GET("speakers.json")
    fun loadSpeakers(): Observable<List<SpeakerResponse>>

    @GET("sessions.json")
    fun loadSessions(): Observable<List<SessionResponse>>

    @GET("videos.json")
    fun loadVideos(): Observable<List<VideoResponse>>
}