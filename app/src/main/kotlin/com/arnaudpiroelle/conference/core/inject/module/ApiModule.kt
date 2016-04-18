package com.arnaudpiroelle.conference.core.inject.module

import com.arnaudpiroelle.conference.BuildConfig
import com.arnaudpiroelle.conference.core.api.ConferenceApiService
import com.arnaudpiroelle.conference.core.utils.Dates
import com.google.gson.*
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides @Singleton fun provideGson(): Gson {
        return GsonBuilder()
                .registerTypeAdapter(Date::class.java, JsonDeserializer<Date> {
                    json: JsonElement, typeOfT: Type, context: JsonDeserializationContext ->

                    try {
                        val dateString = json.asJsonPrimitive.asString
                        Dates.fromUtc(dateString);
                    } catch(e: Exception) {
                        e.printStackTrace()
                        null
                    }
                })
                .create()
    }

    @Provides @Singleton fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides @Singleton fun provideConferenceApiService(retrofit: Retrofit): ConferenceApiService {
        return retrofit.create(ConferenceApiService::class.java)
    }
}