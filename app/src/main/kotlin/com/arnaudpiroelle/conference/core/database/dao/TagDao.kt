package com.arnaudpiroelle.conference.core.database.dao

import android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE
import com.arnaudpiroelle.conference.core.database.mapper.SessionMapper
import com.arnaudpiroelle.conference.core.database.mapper.SpeakerMapper
import com.arnaudpiroelle.conference.core.database.mapper.TagMapper
import com.arnaudpiroelle.conference.core.database.mapper.TagMapper.toContentValues
import com.arnaudpiroelle.conference.core.database.utils.getString
import com.arnaudpiroelle.conference.model.Session
import com.arnaudpiroelle.conference.model.Speaker
import com.arnaudpiroelle.conference.model.Tag
import com.arnaudpiroelle.conference.model.Tag.Companion.TABLE_NAME
import com.arnaudpiroelle.conference.model.rel.SessionSpeaker
import com.arnaudpiroelle.conference.model.rel.SessionTag
import com.squareup.sqlbrite.BriteDatabase
import rx.Observable
import javax.inject.Inject

class TagDao @Inject constructor(val db: BriteDatabase) {

    fun addOrUpdate(tag: Tag) {
        db.insert(TABLE_NAME, toContentValues(tag), CONFLICT_REPLACE)
    }

    fun getTags(): Observable<List<Tag>> {
        return db.createQuery(Tag.TABLE_NAME, "SELECT * FROM ${Tag.TABLE_NAME}")
                .mapToList(TagMapper.toTag)
    }

    fun getTag(id: String): Observable<Tag> {
        return db.createQuery(Tag.TABLE_NAME, "SELECT * FROM ${Tag.TABLE_NAME} WHERE ${Tag.COL_ID} =?", id)
                .mapToOne(TagMapper.toTag)
    }

    fun getTagsSessions(): Observable<Map<String, List<Session>>> {
        return db.createQuery(Tag.TABLE_NAME,
                "SELECT ${SessionTag.TABLE_NAME}.${SessionTag.COL_TAG} as tagId, ${Session.TABLE_NAME}.* from ${SessionTag.TABLE_NAME} " +
                        "left outer join ${Session.TABLE_NAME} " +
                        "on ${SessionTag.TABLE_NAME}.${SessionTag.COL_SESSION}=${Session.TABLE_NAME}.${Session.COL_ID}")
                .mapToList {
                    val tagId = it.getString("tagId")
                    val session = SessionMapper.toSession(it)

                    Pair(tagId, session)
                }
                .flatMap {
                    val groupBy = it.groupBy({
                        it.first!!
                    }, {
                        it.second
                    })
                    Observable.just(groupBy)
                }
    }

    fun getTagsBySessionId(sessionId: String): Observable<List<Tag>> {
        return db.createQuery(Tag.TABLE_NAME, "SELECT ${Tag.TABLE_NAME}.* FROM ${Tag.TABLE_NAME} " +
                "INNER JOIN ${SessionTag.TABLE_NAME} " +
                "ON ${Tag.TABLE_NAME}.${Tag.COL_ID}=${SessionTag.TABLE_NAME}.${SessionTag.COL_TAG} " +
                "WHERE ${SessionTag.COL_SESSION}=?", sessionId)
                .mapToList(TagMapper.toTag)
    }

}
