package com.arnaudpiroelle.conference.core.database

import android.app.Application
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.arnaudpiroelle.conference.model.*
import javax.inject.Inject


class DatabaseOpenHelper @Inject constructor(application: Application) : SQLiteOpenHelper(application, DATABASE_NAME, null, VERSION) {

    companion object {
        val DATABASE_NAME: String = "conference.db"
        val VERSION: Int = 1

        val CREATE_TABLE_BLOCKS = "CREATE TABLE ${Block.TABLE_NAME} (" +
                "${Block.COL_ID} TEXT NOT NULL PRIMARY KEY, " +
                "${Block.COL_TITLE} TEXT, " +
                "${Block.COL_SUBTITLE} TEXT, " +
                "${Block.COL_TYPE} TEXT, " +
                "${Block.COL_START} TEXT, " +
                "${Block.COL_END} TEXT)"

        val CREATE_TABLE_ROOMS = "CREATE TABLE ${Room.TABLE_NAME} (" +
                "${Room.COL_ID} TEXT NOT NULL PRIMARY KEY, " +
                "${Room.COL_NAME} TEXT)"

        val CREATE_TABLE_TAGS = "CREATE TABLE ${Tag.TABLE_NAME} (" +
                "${Tag.COL_ID} TEXT NOT NULL PRIMARY KEY, " +
                "${Tag.COL_NAME} TEXT, " +
                "${Tag.COL_TYPE} TEXT)"

        val CREATE_TABLE_SPEAKERS = "CREATE TABLE ${Speaker.TABLE_NAME} (" +
                "${Speaker.COL_ID} TEXT NOT NULL PRIMARY KEY, " +
                "${Speaker.COL_NAME} TEXT, " +
                "${Speaker.COL_BIO} TEXT, " +
                "${Speaker.COL_COMPANY} TEXT," +
                "${Speaker.COL_THUMBNAIL} TEXT)"

        val CREATE_TABLE_SESSIONS = "CREATE TABLE ${Session.TABLE_NAME} (" +
                "${Session.COL_ID} TEXT NOT NULL PRIMARY KEY, " +
                "${Session.COL_TITLE} TEXT, " +
                "${Session.COL_DESCRIPTION} TEXT, " +
                "${Session.COL_TAGS} TEXT, " +
                "${Session.COL_MAIN_TAG} TEXT, " +
                "${Session.COL_START} TEXT, " +
                "${Session.COL_END} TEXT, " +
                "${Session.COL_PHOTO_URL} TEXT, " +
                "${Session.COL_SPEAKERS} TEXT, " +
                "${Session.COL_ROOM} TEXT)"

        val CREATE_TABLE_VIDEOS = "CREATE TABLE ${Video.TABLE_NAME} (" +
                "${Video.COL_ID} TEXT NOT NULL PRIMARY KEY, " +
                "${Video.COL_TITLE} TEXT, " +
                "${Video.COL_DESCRIPTION} TEXT, " +
                "${Video.COL_THUMBNAIL} TEXT, " +
                "${Video.COL_TOPIC} TEXT, " +
                "${Video.COL_SPEAKERS} TEXT)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_BLOCKS);
        db.execSQL(CREATE_TABLE_ROOMS);
        db.execSQL(CREATE_TABLE_TAGS);
        db.execSQL(CREATE_TABLE_SPEAKERS);
        db.execSQL(CREATE_TABLE_SESSIONS);
        db.execSQL(CREATE_TABLE_VIDEOS);
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

}