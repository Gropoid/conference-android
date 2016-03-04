package com.arnaudpiroelle.conference.core.database.dao

import android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE
import com.arnaudpiroelle.conference.core.database.mapper.SessionMapper.toContentValues
import com.arnaudpiroelle.conference.model.Session
import com.arnaudpiroelle.conference.model.Session.Companion.TABLE_NAME
import com.squareup.sqlbrite.BriteDatabase
import javax.inject.Inject

class SessionDao @Inject constructor(val db: BriteDatabase) {

    fun addOrUpdate(session: Session) {
        db.insert(TABLE_NAME, toContentValues(session), CONFLICT_REPLACE)
    }

}

