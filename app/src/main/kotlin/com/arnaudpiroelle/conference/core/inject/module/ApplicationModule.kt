package com.arnaudpiroelle.conference.core.inject.module

import android.app.Application
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File
import javax.inject.Singleton

@Module
class ApplicationModule(val application: Application) {
    @Provides fun provideApplication(): Application {
        return application
    }

    @Provides @Singleton fun provideOkHttpClient(application: Application): OkHttpClient {
        var cacheDir = File(application.cacheDir, "http");
        var cache = Cache(cacheDir, 31457280);
        return OkHttpClient.Builder().cache(cache).build();
    }
}