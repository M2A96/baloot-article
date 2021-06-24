package com.example.ataei.di.module

import com.example.ataei.BuildConfig
import com.example.ataei.SecretFields
import com.example.data.di.qualifier.Concrete
import com.example.data.di.qualifier.WithoutToken
import com.example.data.source.remote.GameDataSource
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import dagger.Module
import dagger.Provides
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton


@Module
object NetworkModule {

    /**
     * provides Gson with custom [Date] converter for [Long] epoch times
     */
    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            // Deserializer to convert json long value into Date
            .registerTypeAdapter(
                Date::class.java,
                JsonDeserializer { json, _, _ ->
                    Date(json.asJsonPrimitive.asLong)
                }
            )
            // Serializer to convert Date value into long json primitive
            .registerTypeAdapter(
                Date::class.java,
                JsonSerializer<Date> { src, _, _ ->
                    JsonPrimitive(src.time)
                }
            )
            .create()
    }


    /**
     * provides shared [Headers] to be added into [OkHttpClient] instances
     */
    @Singleton
    @Provides
    fun provideSharedHeaders(): Headers {
        return Headers.Builder()
            .add("Content-Type", "application/json; charset=utf-8")
            .build()
    }


    /**
     * provides instance of [OkHttpClient] for without-token api services
     *
     * @param headers default shared headers provided by [provideSharedHeaders]
     * @return an instance of [OkHttpClient]
     */
    @Singleton
    @Provides
    fun provideOkHttpClient(headers: Headers): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)

            builder.addNetworkInterceptor(StethoInterceptor())
        }

        builder.interceptors().add(
            Interceptor { chain ->
                val request = chain.request()
                val requestBuilder = request.newBuilder()
                    .headers(headers)
                    .method(request.method(), request.body())
                chain.proceed(requestBuilder.build())
            }
        )

        return builder.build()
    }

    /**
     * provide an instance of [Retrofit] for without-token api services
     *
     * @param okHttpClient an instance of without-token [okHttpClient] provided by [provideOkHttpClient]
     * @param gson an instance of gson provided by [provideGson] to use as retrofit converter factory
     *
     * @return an instance of [Retrofit] for without-token api calls
     */
    @Singleton
    @Provides
    @WithoutToken
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson,
        secretFields: SecretFields
    ): Retrofit {
        return Retrofit.Builder().client(okHttpClient)
            // create gson converter factory
            .addConverterFactory(GsonConverterFactory.create(gson))
            // get base url from SecretFields interface
            .baseUrl(secretFields.getBaseUrl())
            .build()
    }


    @Provides
    @Concrete
    fun provideConcreteGameDataSource(@WithoutToken retrofit: Retrofit): GameDataSource {
        return retrofit.create(GameDataSource::class.java)
    }

}