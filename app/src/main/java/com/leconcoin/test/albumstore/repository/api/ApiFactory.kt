package com.leconcoin.test.albumstore.repository.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiFactory {

    companion object {

        private const val API_BASE_URL = "https://static.leboncoin.fr/"

        fun buildApi(): IAlbumAPI {
            return Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(IAlbumAPI::class.java)
        }
    }
}