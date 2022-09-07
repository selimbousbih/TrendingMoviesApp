package com.selim.trends.networking

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.selim.trends.application.Config.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    internal fun provideOkHttpCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(cache: Cache, interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideDefaultInterceptor(): Interceptor {
        return Interceptor { chain ->
            chain.request().let {
                val newUrlBuilder = it.url.newBuilder()
                    .addEncodedQueryParameter(CLIENT_ID_KEY, CLIENT_ID)

                chain.proceed(
                    it.newBuilder()
                        .url(newUrlBuilder.build())
                        .build()
                )
            }
        }
    }

    @Provides
    @Singleton
    internal fun providesApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    companion object {
        const val CLIENT_ID_KEY = "api_key"
        const val CLIENT_ID = "c9856d0cb57c3f14bf75bdc6c063b8f3"
    }
}