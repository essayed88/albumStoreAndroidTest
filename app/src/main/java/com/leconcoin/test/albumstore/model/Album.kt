package com.leconcoin.test.albumstore.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "albums")
data class Album(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val albumId: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)