package com.leconcoin.test.albumstore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leconcoin.test.albumstore.BaseViewModel
import com.leconcoin.test.albumstore.model.Album
import com.leconcoin.test.albumstore.model.AlbumDao
import com.leconcoin.test.albumstore.repository.api.ApiFactory
import com.leconcoin.test.albumstore.repository.api.IAlbumAPI
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AlbumListViewModelImpl(private val albumDao: AlbumDao) : BaseViewModel(),
    IAlbumListViewModel {

    private lateinit var subscription: Disposable

    private var albumApi: IAlbumAPI = ApiFactory.buildApi()

    private var retrieveAlbumsDataEvent = MutableLiveData<List<Album>>()

    private var startRetrieveDataEvent = MutableLiveData<Any>()

    private var finishRetrieveDataEvent = MutableLiveData<Any>()

    private var error = MutableLiveData<Throwable>()

    override fun observeAlbums(): LiveData<List<Album>> = retrieveAlbumsDataEvent

    override fun observeStartRetrieveData() = startRetrieveDataEvent

    override fun observeFinishRetrieveData() = finishRetrieveDataEvent

    override fun observeError() = error

    override fun loadAlbums() {
        subscription = Observable.fromCallable { albumDao.getAllAlbums }
            .concatMap { dbAlbumList ->
                if (dbAlbumList.isEmpty())
                    albumApi.getAlbums().concatMap { apiAlbumList ->
                        albumDao.insertAll(apiAlbumList)
                        Observable.just(apiAlbumList)
                    }
                else
                    Observable.just(dbAlbumList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { startRetrieveDataEvent.postValue(null) }
            .doOnTerminate { finishRetrieveDataEvent.postValue(null) }
            .subscribe(
                { result -> retrieveAlbumsDataEvent.postValue(result) },
                { error.postValue(it) }
            )
        return
    }
}