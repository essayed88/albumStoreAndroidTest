package com.leconcoin.test.albumstore.view.albumDetail

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.leconcoin.test.albumstore.R
import com.leconcoin.test.albumstore.di.ViewModelFactory
import com.leconcoin.test.albumstore.model.Album
import com.leconcoin.test.albumstore.model.AlbumDao
import com.leconcoin.test.albumstore.view.BaseActivity
import com.leconcoin.test.albumstore.viewmodel.AlbumDetailViewModelImpl
import com.leconcoin.test.albumstore.viewmodel.IAlbumDetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_album_detail.*
import javax.inject.Inject


class AlbumDetailActivity : BaseActivity(R.layout.activity_album_detail) {

    companion object {
        const val EXTRA_BOOK_ID = "albumId"
    }

    @Inject
    lateinit var viewModel: IAlbumDetailViewModel

    @Inject
    lateinit var albumDao: AlbumDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val albumId = intent.getIntExtra(EXTRA_BOOK_ID, 1)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this))
            .get(AlbumDetailViewModelImpl::class.java)
        viewModel.getAlbumById(albumId.toString())
        viewModel.observeAlbum().observe(this, Observer { album -> updateAlbum(album!!) })
    }

    private fun updateAlbum(album: Album) {
        Picasso.get()
            .load(album.url)
            .placeholder(R.drawable.ic_placeholder_image)
            .into(albumCover)

        albumTitle.text = album.title
        albumId.text = album.albumId.toString()
    }


}