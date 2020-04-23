package com.leconcoin.test.albumstore.repository.local


import androidx.lifecycle.MutableLiveData
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.SingleSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


fun <T> Single<T>.ioToMainThread(): Single<T> = subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())

fun Completable.ioToMainThread(): Completable = subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.asyncToMainThread(): Single<T> = subscribeOn(Schedulers.computation())
    .observeOn(AndroidSchedulers.mainThread())

fun <T> Flowable<T>.ioToMainThread(): Flowable<T> = subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.ioToMainThread(): Observable<T> = subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.fetch(errorEvent: MutableLiveData<Throwable>) {
    doOnError { errorEvent.postValue(it) }
        .ioToMainThread()
}

fun <T> Flowable<T>.fetch(errorEvent: MutableLiveData<Throwable>) {
    doOnError {
        errorEvent.postValue(it)
    }
        .ioToMainThread()
}


fun <T> Call<T>.toSingle(): Single<Response<T>> {
    val single = SingleSubject.create<Response<T>>()
    this.enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
            single.onError(t)
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            single.onSuccess(response)
        }
    })

    return single
}