package com.arnaudpiroelle.conference.core.database.dao

import android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE
import com.arnaudpiroelle.conference.core.database.mapper.TagMapper.toContentValues
import com.arnaudpiroelle.conference.model.Tag
import com.arnaudpiroelle.conference.model.Tag.Companion.TABLE_NAME
import com.squareup.sqlbrite.BriteDatabase
import javax.inject.Inject

class TagDao @Inject constructor(val db: BriteDatabase) {

    fun addOrUpdate(tag: Tag) {
        db.insert(TABLE_NAME, toContentValues(tag), CONFLICT_REPLACE)
    }

}
