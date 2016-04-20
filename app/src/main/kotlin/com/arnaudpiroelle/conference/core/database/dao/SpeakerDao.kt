package com.arnaudpiroelle.conference.core.database.dao

import android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE
import com.arnaudpiroelle.conference.core.database.mapper.SpeakerMapper
import com.arnaudpiroelle.conference.core.database.mapper.SpeakerMapper.toContentValues
import com.arnaudpiroelle.conference.model.Speaker
import com.arnaudpiroelle.conference.model.Speaker.Companion.TABLE_NAME
import com.squareup.sqlbrite.BriteDatabase
import rx.Observable
import javax.inject.Inject

class SpeakerDao @Inject constructor(val db: BriteDatabase) {

    fun addOrUpdate(speaker: Speaker) {
        db.insert(TABLE_NAME, toContentValues(speaker), CONFLICT_REPLACE)
    }

    fun getSpeakers(): Observable<List<Speaker>> {
        return db.createQuery(Speaker.TABLE_NAME, "SELECT * FROM ${Speaker.TABLE_NAME}")
                .mapToList {
                    SpeakerMapper.toSpeaker(it)
                }
    }

    fun getSpeaker(id: String): Observable<Speaker> {
        return db.createQuery(Speaker.TABLE_NAME, "SELECT * FROM ${Speaker.TABLE_NAME} WHERE ${Speaker.COL_ID} =?", id)
                .mapToOne {
                    SpeakerMapper.toSpeaker(it)
                }
    }

}