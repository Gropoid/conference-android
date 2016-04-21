package com.arnaudpiroelle.conference.core.database.dao

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE
import com.arnaudpiroelle.conference.core.database.mapper.SessionMapper
import com.arnaudpiroelle.conference.core.database.mapper.SessionMapper.toContentValues
import com.arnaudpiroelle.conference.core.database.mapper.SessionSpeakerMapper
import com.arnaudpiroelle.conference.core.database.mapper.SessionTagMapper
import com.arnaudpiroelle.conference.model.Session
import com.arnaudpiroelle.conference.model.Session.Companion.TABLE_NAME
import com.arnaudpiroelle.conference.model.rel.SessionSpeaker
import com.arnaudpiroelle.conference.model.rel.SessionTag
import com.squareup.sqlbrite.BriteDatabase
import rx.Observable
import javax.inject.Inject

class SessionDao @Inject constructor(val db: BriteDatabase) {

    fun addOrUpdate(session: Session) {
        db.insert(TABLE_NAME, toContentValues(session), CONFLICT_REPLACE)
    }

    fun addOrUpdate(sessionSpeaker: SessionSpeaker) {
        db.insert(SessionSpeaker.TABLE_NAME, SessionSpeakerMapper.toContentValues(sessionSpeaker), SQLiteDatabase.CONFLICT_REPLACE)
    }

    fun addOrUpdate(sessionTag: SessionTag) {
        db.insert(SessionTag.TABLE_NAME, SessionTagMapper.toContentValues(sessionTag), SQLiteDatabase.CONFLICT_REPLACE)
    }

    fun getKeynotes(): Observable<List<Session>> {
        return db.createQuery(Session.TABLE_NAME, "SELECT * FROM ${Session.TABLE_NAME} WHERE ${Session.COL_MAIN_TAG}=? LIMIT 1", "FLAG_KEYNOTE")
                .mapToList {
                    SessionMapper.toSession(it)
                }
    }

    fun getSessionsByTag(tagId: String): Observable<List<Session>> {
        return db.createQuery(Session.TABLE_NAME, "SELECT ${Session.TABLE_NAME}.* FROM ${Session.TABLE_NAME} " +
                "INNER JOIN ${SessionTag.TABLE_NAME} " +
                "ON ${Session.TABLE_NAME}.${Session.COL_ID}=${SessionTag.TABLE_NAME}.${SessionTag.COL_SESSION} " +
                "WHERE ${SessionTag.COL_TAG}=?", tagId)
                .mapToList(SessionMapper.toSession)
    }

    fun getSessionsBySpeaker(speakerId: String): Observable<List<Session>> {
        return db.createQuery(Session.TABLE_NAME, "SELECT ${Session.TABLE_NAME}.* FROM ${Session.TABLE_NAME} " +
                "INNER JOIN ${SessionSpeaker.TABLE_NAME} " +
                "ON ${Session.TABLE_NAME}.${Session.COL_ID}=${SessionSpeaker.TABLE_NAME}.${SessionSpeaker.COL_SESSION} " +
                "WHERE ${SessionSpeaker.COL_SPEAKER}=?", speakerId)
                .mapToList(SessionMapper.toSession)
    }

    fun getSessions(): Observable<List<Session>> {
        return db.createQuery(Session.TABLE_NAME, "SELECT * FROM ${Session.TABLE_NAME} WHERE ${Session.COL_MAIN_TAG} NOT LIKE ?", "FLAG_KEYNOTE")
                .mapToList(SessionMapper.toSession)
    }

    fun getSession(id: String): Observable<Session> {
        return db.createQuery(Session.TABLE_NAME, "SELECT * FROM ${Session.TABLE_NAME} WHERE ${Session.COL_ID} =?", id)
                .mapToOne(SessionMapper.toSession)
    }

}

