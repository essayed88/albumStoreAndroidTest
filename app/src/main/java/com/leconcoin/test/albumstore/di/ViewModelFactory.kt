package com.leconcoin.test.albumstore.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.leconcoin.test.albumstore.viewmodel.AlbumDetailViewModelImpl
import com.leconcoin.test.albumstore.viewmodel.AlbumListViewModelImpl
import com.leconcoin.test.albumstore.model.database.AppDatabase


class ViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlbumListViewModelImpl::class.java)) {
            val db =
                Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "albums")
                    .build()
            @Suppress("UNCHECKED_CAST")
            return AlbumListViewModelImpl(
                db.albumDao()
            ) as T
        } else if (modelClass.isAssignableFrom(AlbumDetailViewModelImpl::class.java)) {
            val db =
                Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "albums")
                    .build()
            @Suppress("UNCHECKED_CAST")
            return AlbumDetailViewModelImpl(
                db.albumDao()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}