package com.arnaudpiroelle.conference.core.api

import com.arnaudpiroelle.conference.model.*
import retrofit2.http.GET
import rx.Observable


interface ConferenceApiService {
    @GET("/blocks")
    fun loadBlocks(): Observable<List<Block>>

    @GET("/rooms")
    fun loadRooms(): Observable<List<Room>>

    @GET("/tags")
    fun loadTags(): Observable<List<Tag>>

    @GET("/speakers")
    fun loadSpeakers(): Observable<List<Speaker>>

    @GET("/sessions")
    fun loadSessions(): Observable<List<Session>>

    @GET("/videos")
    fun loadVideos(): Observable<List<Video>>
}