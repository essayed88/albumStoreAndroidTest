package com.leconcoin.test.albumstore.di.component

import com.leconcoin.test.albumstore.viewmodel.AlbumDetailViewModelImpl
import com.leconcoin.test.albumstore.viewmodel.AlbumListViewModelImpl
import dagger.Component
import com.leconcoin.test.albumstore.di.module.NetworkModule
import javax.inject.Singleton


@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {


    fun inject(albumListViewModel: AlbumListViewModelImpl)

    fun inject(albumDetailViewModel: AlbumDetailViewModelImpl)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}