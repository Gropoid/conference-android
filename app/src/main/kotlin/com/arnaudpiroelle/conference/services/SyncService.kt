package com.arnaudpiroelle.conference.services

import android.app.IntentService
import android.content.Intent
import com.arnaudpiroelle.conference.ConferenceApplication.Companion.GRAPH
import com.arnaudpiroelle.conference.core.api.ConferenceApiService
import com.arnaudpiroelle.conference.core.api.response.*
import com.arnaudpiroelle.conference.core.database.dao.*
import com.arnaudpiroelle.conference.model.*
import com.arnaudpiroelle.conference.model.rel.SessionSpeaker
import com.arnaudpiroelle.conference.model.rel.SessionTag
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
                conferenceApiService.loadBlocks(),
                conferenceApiService.loadRooms(),
                conferenceApiService.loadTags(),
                conferenceApiService.loadSpeakers(),
                conferenceApiService.loadSessions(),
                conferenceApiService.loadVideos(),
                {
                    blocks, rooms, tags, speakers, sessions, videos ->

                    SyncResult(blocks, rooms, tags, speakers, sessions, videos)
                })
                .subscribeOn(Schedulers.io())
                .subscribe {

                    val newTransaction = db.newTransaction()

                    fillBlocks(it)
                    fillRooms(it)
                    fillSpeakers(it)
                    fillTags(it)
                    fillVideos(it)
                    fillSessions(it)

                    newTransaction.markSuccessful()
                    newTransaction.end()
                }

    }

    private fun fillBlocks(syncResult: SyncResult) {
        Timber.i("Update blocks informations")

        syncResult.blocks.forEach {
            blockDao.addOrUpdate(Block(it.id, it.title, it.subtitle, it.type, it.start, it.end))
        }
    }

    private fun fillRooms(syncResult: SyncResult) {
        Timber.i("Update rooms informations")

        syncResult.rooms.forEach {
            roomDao.addOrUpdate(Room(it.id, it.name))
        }
    }

    private fun fillTags(syncResult: SyncResult) {
        Timber.i("Update tags informations")

        syncResult.tags.forEach {
            tagDao.addOrUpdate(Tag(it.id, it.name, it.type))
        }
    }

    private fun fillSpeakers(syncResult: SyncResult) {
        Timber.i("Update speakers informations")

        syncResult.speakers.forEach {
            speakerDao.addOrUpdate(Speaker(it.id, it.name, it.bio, it.company, it.thumbnailUrl, it.twitter, it.github, it.website))
        }
    }

    private fun fillSessions(syncResult: SyncResult) {
        Timber.i("Update sessions informations")

        syncResult.sessions.forEach {

            val session = Session(it.id, it.title, it.description, it.mainTag, it.start, it.end, it.photoUrl, it.room)

            it.speakers?.forEach { speakerId ->
                val sessionSpeaker = SessionSpeaker(session.id!!, speakerId)
                sessionDao.addOrUpdate(sessionSpeaker)
            }

            it.tags?.forEach { tagId ->
                val sessionTag = SessionTag(session.id!!, tagId)
                sessionDao.addOrUpdate(sessionTag)
            }

            sessionDao.addOrUpdate(session)
        }
    }

    private fun fillVideos(syncResult: SyncResult) {
        Timber.i("Update videos informations")

        syncResult.videos.forEach {
            videoDao.addOrUpdate(Video(it.id, it.title, it.description, it.thumbnailUrl, it.topic, it.speakers))
        }

    }

    data class SyncResult(val blocks: List<BlockResponse>, val rooms: List<RoomResponse>, val tags: List<TagResponse>, val speakers: List<SpeakerResponse>, val sessions: List<SessionResponse>, val videos: List<VideoResponse>)

}