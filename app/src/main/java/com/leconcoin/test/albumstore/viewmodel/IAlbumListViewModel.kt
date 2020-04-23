package com.leconcoin.test.albumstore.viewmodel

import androidx.lifecycle.LiveData
import com.leconcoin.test.albumstore.model.Album

interface IAlbumListViewModel {

    fun observeAlbums(): LiveData<List<Album>>
    fun observeError(): LiveData<Throwable>
    fun observeStartRetrieveData(): LiveData<Any>
    fun observeFinishRetrieveData(): LiveData<Any>
    fun loadAlbums()
}