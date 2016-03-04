package com.arnaudpiroelle.conference.core.database.dao

import android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE
import com.arnaudpiroelle.conference.core.database.mapper.SpeakerMapper.toContentValues
import com.arnaudpiroelle.conference.model.Speaker
import com.arnaudpiroelle.conference.model.Speaker.Companion.TABLE_NAME
import com.squareup.sqlbrite.BriteDatabase
import javax.inject.Inject

class SpeakerDao @Inject constructor(val db: BriteDatabase) {

    fun addOrUpdate(speaker: Speaker) {
        db.insert(TABLE_NAME, toContentValues(speaker), CONFLICT_REPLACE)
    }

}