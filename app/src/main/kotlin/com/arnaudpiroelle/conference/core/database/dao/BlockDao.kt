package com.arnaudpiroelle.conference.core.database.dao

import android.database.sqlite.SQLiteDatabase.CONFLICT_REPLACE
import com.arnaudpiroelle.conference.core.database.mapper.BlockMapper.toContentValues
import com.arnaudpiroelle.conference.model.Block
import com.arnaudpiroelle.conference.model.Block.Companion.TABLE_NAME
import com.squareup.sqlbrite.BriteDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton class BlockDao @Inject constructor(val db: BriteDatabase) {

    fun addOrUpdate(block: Block) {
        db.insert(TABLE_NAME, toContentValues(block), CONFLICT_REPLACE)
    }
}