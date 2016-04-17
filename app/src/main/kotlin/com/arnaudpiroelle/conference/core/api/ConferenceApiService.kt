package com.arnaudpiroelle.conference.core.api

import com.arnaudpiroelle.conference.model.*
import retrofit2.http.GET
import rx.Observable


interface ConferenceApiService {
    @GET("blocks.json")
    fun loadBlocks(): Observable<List<Block>>

    @GET("rooms.json")
    fun loadRooms(): Observable<List<Room>>

    @GET("tags.json")
    fun loadTags(): Observable<List<Tag>>

    @GET("speakers.json")
    fun loadSpeakers(): Observable<List<Speaker>>

    @GET("sessions.json")
    fun loadSessions(): Observable<List<Session>>

    @GET("videos.json")
    fun loadVideos(): Observable<List<Video>>
}