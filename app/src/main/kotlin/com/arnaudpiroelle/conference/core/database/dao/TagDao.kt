package com.arnaudpiroelle.conference.core.database.dao

import android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE
import com.arnaudpiroelle.conference.core.database.mapper.TagMapper
import com.arnaudpiroelle.conference.core.database.mapper.TagMapper.toContentValues
import com.arnaudpiroelle.conference.model.Tag
import com.arnaudpiroelle.conference.model.Tag.Companion.TABLE_NAME
import com.squareup.sqlbrite.BriteDatabase
import rx.Observable
import javax.inject.Inject

class TagDao @Inject constructor(val db: BriteDatabase) {

    fun addOrUpdate(tag: Tag) {
        db.insert(TABLE_NAME, toContentValues(tag), CONFLICT_REPLACE)
    }

    fun getTags(): Observable<List<Tag>> {
        return db.createQuery(Tag.TABLE_NAME, "SELECT * FROM ${Tag.TABLE_NAME}")
                .mapToList {
                    TagMapper.toTag(it)
                }
    }

    fun getTag(id: String): Observable<Tag> {
        return db.createQuery(Tag.TABLE_NAME, "SELECT * FROM ${Tag.TABLE_NAME} WHERE ${Tag.COL_ID} =?", id)
                .mapToOne {
                    TagMapper.toTag(it)
                }
    }

}
