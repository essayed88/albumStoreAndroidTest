package com.leconcoin.test.albumstore.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.leconcoin.test.albumstore.model.Album
import com.leconcoin.test.albumstore.model.AlbumDao

@Database(entities = [Album::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}