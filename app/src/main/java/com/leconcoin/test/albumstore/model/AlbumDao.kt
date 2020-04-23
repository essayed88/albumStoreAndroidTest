package com.leconcoin.test.albumstore.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface AlbumDao {
    @get:Query("SELECT * FROM albums")
    val getAllAlbums: List<Album>

    @Insert
    fun insertAll(albums: List<Album>)

    @Query("SELECT * FROM albums WHERE id = :id")
    fun getAlbumById(id: String): Album

}