package com.leconcoin.test.albumstore.viewmodel

import androidx.lifecycle.MutableLiveData
import com.leconcoin.test.albumstore.BaseViewModel
import com.leconcoin.test.albumstore.model.Album
import com.leconcoin.test.albumstore.model.AlbumDao
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AlbumDetailViewModelImpl(private val albumDao: AlbumDao): BaseViewModel(),
    IAlbumDetailViewModel {

    private var albumEvent: MutableLiveData<Album> = MutableLiveData()
    private var error: MutableLiveData<Throwable> = MutableLiveData()
    private lateinit var subscription: Disposable

    override fun observeAlbum(): MutableLiveData<Album> = albumEvent
    override fun observeError(): MutableLiveData<Throwable> = error

    override fun getAlbumById(albumId: String) {
        subscription = Observable.fromCallable { albumDao.getAlbumById(albumId) }
            .concatMap { dbAlbum ->
                Observable.just(dbAlbum)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> albumEvent.postValue(result) },
                { error.postValue(it) }
            )

    }


    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}