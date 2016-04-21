package com.arnaudpiroelle.conference.core.database.dao

import android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE
import com.arnaudpiroelle.conference.core.database.mapper.SessionMapper
import com.arnaudpiroelle.conference.core.database.mapper.SessionMapper.toContentValues
import com.arnaudpiroelle.conference.model.Session
import com.arnaudpiroelle.conference.model.Session.Companion.TABLE_NAME
import com.squareup.sqlbrite.BriteDatabase
import rx.Observable
import javax.inject.Inject

class SessionDao @Inject constructor(val db: BriteDatabase) {

    fun addOrUpdate(session: Session) {
        db.insert(TABLE_NAME, toContentValues(session), CONFLICT_REPLACE)
    }

    fun getKeynotes(): Observable<List<Session>> {
        return db.createQuery(Session.TABLE_NAME, "SELECT * FROM ${Session.TABLE_NAME} WHERE ${Session.COL_MAIN_TAG}=? LIMIT 1", "FLAG_KEYNOTE")
                .mapToList {
                    SessionMapper.toSession(it)
                }
    }

    fun getSessionsByTag(tag: String): Observable<List<Session>> {
        return db.createQuery(Session.TABLE_NAME, "SELECT * FROM ${Session.TABLE_NAME} WHERE ${Session.COL_TAGS} LIKE ?", "%$tag%")
                .mapToList {
                    SessionMapper.toSession(it)
                }
    }

    fun getSessionsBySpeaker(speaker: String): Observable<List<Session>> {
        return db.createQuery(Session.TABLE_NAME, "SELECT * FROM ${Session.TABLE_NAME} WHERE ${Session.COL_SPEAKERS} LIKE ?", "%$speaker%")
                .mapToList {
                    SessionMapper.toSession(it)
                }
    }

    fun getSessions(): Observable<List<Session>> {
        return db.createQuery(Session.TABLE_NAME, "SELECT * FROM ${Session.TABLE_NAME} WHERE ${Session.COL_MAIN_TAG} NOT LIKE ?", "FLAG_KEYNOTE")
                .mapToList {
                    SessionMapper.toSession(it)
                }
    }

    fun getSession(id: String): Observable<Session> {
        return db.createQuery(Session.TABLE_NAME, "SELECT * FROM ${Session.TABLE_NAME} WHERE ${Session.COL_ID} =?", id)
                .mapToOne {
                    SessionMapper.toSession(it)
                }
    }

}

