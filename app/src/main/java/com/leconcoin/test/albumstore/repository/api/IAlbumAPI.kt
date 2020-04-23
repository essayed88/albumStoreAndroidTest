package com.leconcoin.test.albumstore.repository.api

import com.leconcoin.test.albumstore.model.Album
import io.reactivex.Observable
import retrofit2.http.GET


interface IAlbumAPI {
    @GET("img/shared/technical-test.json")
    fun getAlbums(): Observable<List<Album>>
}