package com.arnaudpiroelle.conference.core.inject.module

import com.arnaudpiroelle.conference.core.database.DatabaseOpenHelper
import com.squareup.sqlbrite.BriteDatabase
import com.squareup.sqlbrite.SqlBrite
import dagger.Module
import dagger.Provides
import rx.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides @Singleton fun provideSqlBrite(): SqlBrite {
        return SqlBrite.create({
            Timber.d(it)
        });
    }


    @Provides @Singleton fun provideBrideDatabase(sqlBrite: SqlBrite, helper: DatabaseOpenHelper): BriteDatabase {
        var db = sqlBrite.wrapDatabaseHelper(helper, Schedulers.io());
        db.setLoggingEnabled(true);
        return db;
    }

}