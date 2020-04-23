package com.leconcoin.test.albumstore

import androidx.lifecycle.ViewModel
import com.leconcoin.test.albumstore.viewmodel.AlbumDetailViewModelImpl
import com.leconcoin.test.albumstore.viewmodel.AlbumListViewModelImpl
import com.leconcoin.test.albumstore.di.component.ViewModelInjector
import com.leconcoin.test.albumstore.di.module.NetworkModule
import com.leconcoin.test.albumstore.di.component.DaggerViewModelInjector

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is AlbumListViewModelImpl -> injector.inject(this)
            is AlbumDetailViewModelImpl -> injector.inject(this)
        }
    }
}