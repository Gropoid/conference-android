package com.arnaudpiroelle.conference.core.database.dao

import android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE
import com.arnaudpiroelle.conference.core.database.mapper.RoomMapper.toContentValues
import com.arnaudpiroelle.conference.model.Room
import com.arnaudpiroelle.conference.model.Room.Companion.TABLE_NAME
import com.squareup.sqlbrite.BriteDatabase
import javax.inject.Inject

class RoomDao @Inject constructor(val db: BriteDatabase) {

    fun addOrUpdate(room: Room) {
        db.insert(TABLE_NAME, toContentValues(room), CONFLICT_REPLACE)
    }

}