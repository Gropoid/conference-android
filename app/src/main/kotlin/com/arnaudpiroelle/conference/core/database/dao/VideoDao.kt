package com.arnaudpiroelle.conference.core.database.dao

import android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE
import com.arnaudpiroelle.conference.core.database.mapper.VideoMapper.toContentValues
import com.arnaudpiroelle.conference.model.Video
import com.squareup.sqlbrite.BriteDatabase
import javax.inject.Inject

class VideoDao @Inject constructor(val db: BriteDatabase) {

    fun addOrUpdate(video: Video) {
        db.insert(Video.TABLE_NAME, toContentValues(video), CONFLICT_REPLACE)
    }

}