package com.arnaudpiroelle.conference.core.inject.module

import android.app.Application
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
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

        var logging = HttpLoggingInterceptor({
            Timber.tag("OkHttp").d(it);
        }).setLevel(HttpLoggingInterceptor.Level.BASIC);

        return OkHttpClient.Builder().addInterceptor(logging).cache(cache).build();
    }

    @Provides @Singleton fun providePicasso(application: Application, okHttpClient: OkHttpClient): Picasso {
        val picasso = Picasso.Builder(application)
                .downloader(OkHttp3Downloader(okHttpClient))
                .listener { picasso, uri, exception ->
                    Timber.e(exception, "Failed to load image: %s", uri)
                }
                .build()

        Picasso.setSingletonInstance(picasso)

        return picasso;
    }
}