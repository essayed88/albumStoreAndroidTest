package com.leconcoin.test.albumstore.di.module

import com.leconcoin.test.albumstore.repository.api.IAlbumAPI
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Module which provides all required dependencies about network
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object NetworkModule {
    private const val API_BASE_URL = "https://static.leboncoin.fr/"
    /**
     * Provides the Album service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Album service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideIAlbumAPI(retrofit: Retrofit): IAlbumAPI {
        return retrofit.create(IAlbumAPI::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }
}