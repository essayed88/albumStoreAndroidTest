package com.leconcoin.test.albumstore.viewmodel

import androidx.lifecycle.MutableLiveData
import com.leconcoin.test.albumstore.model.Album

interface IAlbumDetailViewModel {

    fun observeAlbum(): MutableLiveData<Album>
    fun observeError(): MutableLiveData<Throwable>
    fun getAlbumById(albumId: String)
}