package com.arnaudpiroelle.conference.services

import android.app.IntentService
import android.content.Intent
import com.arnaudpiroelle.conference.ConferenceApplication.Companion.GRAPH
import com.arnaudpiroelle.conference.core.api.ConferenceApiService
import com.arnaudpiroelle.conference.core.database.dao.*
import rx.Observable
import timber.log.Timber
import javax.inject.Inject


class SyncService : IntentService("SyncService") {

    @Inject lateinit var conferenceApiService: ConferenceApiService

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
        fillBlocks()
        fillRooms()
        fillTags()
        fillSpeakers()
        fillSessions()
        fillVideos()
    }

    private fun fillBlocks() {
        Timber.i("Update blocks informations")

        conferenceApiService.loadBlocks()
                .flatMap { Observable.from(it) }
                .forEach {
                    Timber.d("$it")
                    blockDao.addOrUpdate(it)
                }
    }

    private fun fillRooms() {
        Timber.i("Update rooms informations")

        conferenceApiService.loadRooms()
                .flatMap { Observable.from(it) }
                .forEach {
                    Timber.d("$it")
                    roomDao.addOrUpdate(it)
                }
    }

    private fun fillTags() {
        Timber.i("Update tags informations")

        conferenceApiService.loadTags()
                .flatMap { Observable.from(it) }
                .forEach {
                    Timber.d("$it")
                    tagDao.addOrUpdate(it)
                }
    }

    private fun fillSpeakers() {
        Timber.i("Update speakers informations")

        conferenceApiService.loadSpeakers()
                .flatMap { Observable.from(it) }
                .forEach {
                    Timber.d("$it")
                    speakerDao.addOrUpdate(it)
                }
    }

    private fun fillSessions() {
        Timber.i("Update sessions informations")

        conferenceApiService.loadSessions()
                .flatMap { Observable.from(it) }
                .forEach {
                    Timber.d("$it")
                    sessionDao.addOrUpdate(it)
                }
    }

    private fun fillVideos() {
        Timber.i("Update videos informations")

        conferenceApiService.loadVideos()
                .flatMap { Observable.from(it) }
                .forEach {
                    Timber.d("$it")
                    videoDao.addOrUpdate(it)
                }
    }
}