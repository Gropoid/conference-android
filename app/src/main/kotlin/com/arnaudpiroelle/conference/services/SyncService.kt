package com.arnaudpiroelle.conference.services

import android.app.IntentService
import android.content.Intent
import com.arnaudpiroelle.conference.ConferenceApplication.Companion.GRAPH
import com.arnaudpiroelle.conference.core.api.ConferenceApiService
import com.arnaudpiroelle.conference.core.database.dao.*
import com.arnaudpiroelle.conference.model.*
import com.squareup.sqlbrite.BriteDatabase
import rx.Observable
import rx.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


class SyncService : IntentService("SyncService") {

    @Inject lateinit var conferenceApiService: ConferenceApiService

    @Inject lateinit var db: BriteDatabase
    @Inject lateinit var blockDao: BlockDao
    @Inject lateinit var roomDao: RoomDao
    @Inject lateinit var sessionDao: SessionDao
    @Inject lateinit var speakerDao: SpeakerDao
    @Inject lateinit var tagDao: TagDao
    @Inject lateinit var videoDao: VideoDao

    override fun onCreate() {
        super.onCreate()

        GRAPH.inject(this)
    }

    override fun onHandleIntent(intent: Intent) {

        Observable.zip(
                fillBlocks(),
                fillRooms(),
                fillTags(),
                fillSpeakers(),
                fillSessions(),
                fillVideos(),
                {
                    blocks, rooms, tags, speakers, sessions, videos ->

                    SyncResult(blocks, rooms, tags, speakers, sessions, videos)
                })
                .subscribeOn(Schedulers.io())
                .subscribe {

                    val newTransaction = db.newTransaction()

                    it.blocks.forEach {
                        blockDao.addOrUpdate(it)
                    }

                    it.rooms.forEach {
                        roomDao.addOrUpdate(it)
                    }

                    it.tags.forEach {
                        tagDao.addOrUpdate(it)
                    }

                    it.speakers.forEach {
                        speakerDao.addOrUpdate(it)
                    }

                    it.sessions.forEach {
                        sessionDao.addOrUpdate(it)
                    }

                    it.videos.forEach {
                        videoDao.addOrUpdate(it)
                    }


                    newTransaction.markSuccessful()
                    newTransaction.end()
                }

    }

    private fun fillBlocks(): Observable<List<Block>> {
        Timber.i("Update blocks informations")

        return conferenceApiService.loadBlocks()
    }

    private fun fillRooms(): Observable<List<Room>> {
        Timber.i("Update rooms informations")

        return conferenceApiService.loadRooms()
    }

    private fun fillTags(): Observable<List<Tag>> {
        Timber.i("Update tags informations")

        return conferenceApiService.loadTags()
    }

    private fun fillSpeakers(): Observable<List<Speaker>> {
        Timber.i("Update speakers informations")

        return conferenceApiService.loadSpeakers()
    }

    private fun fillSessions(): Observable<List<Session>> {
        Timber.i("Update sessions informations")

        return conferenceApiService.loadSessions()
    }

    private fun fillVideos(): Observable<List<Video>> {
        Timber.i("Update videos informations")

        return conferenceApiService.loadVideos()
    }

    data class SyncResult(val blocks: List<Block>, val rooms: List<Room>, val tags: List<Tag>, val speakers: List<Speaker>, val sessions: List<Session>, val videos: List<Video>)

}