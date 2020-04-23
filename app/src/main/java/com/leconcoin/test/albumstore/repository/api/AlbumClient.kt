package com.leconcoin.test.albumstore.repository.api

import io.reactivex.Observable
import com.leconcoin.test.albumstore.model.Album
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumClient @Inject constructor() {

    private var albumApi: IAlbumAPI = ApiFactory.buildApi()

    fun getAlbums(): Observable<List<Album>> = albumApi.getAlbums()
}