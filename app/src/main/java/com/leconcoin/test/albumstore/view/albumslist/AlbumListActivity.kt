package com.leconcoin.test.albumstore.view.albumslist


import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.leconcoin.test.albumstore.R
import com.leconcoin.test.albumstore.di.ViewModelFactory
import com.leconcoin.test.albumstore.model.Album
import com.leconcoin.test.albumstore.view.BaseActivity
import com.leconcoin.test.albumstore.view.albumDetail.AlbumDetailActivity
import com.leconcoin.test.albumstore.viewmodel.AlbumListViewModelImpl
import kotlinx.android.synthetic.main.activity_album_list.*
import timber.log.Timber
import javax.inject.Inject


class AlbumsListActivity : BaseActivity(R.layout.activity_album_list),
    AlbumsListAdapter.AlbumsListAdapterListener {

    @Inject
    lateinit var viewModel: AlbumListViewModelImpl
    private lateinit var albumsAdapter: AlbumsListAdapter
    private lateinit var albums: MutableList<Album>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(this))
            .get(AlbumListViewModelImpl::class.java)
        initUI()
        initData()
    }


    override fun onResume() {
        super.onResume()
        viewModel.loadAlbums()

    }

    private fun initUI() {
        albums = mutableListOf()
        albumsAdapter = AlbumsListAdapter(albums, this)

        recyclerView?.apply {
            adapter = albumsAdapter
            layoutManager = LinearLayoutManager(this@AlbumsListActivity)
        }
        swipeRefresh.setOnRefreshListener {
            viewModel.loadAlbums()
        }
    }

    private fun initData() {
        viewModel.observeAlbums().observe(this, Observer {
            updateAlbums(it)
        })
        viewModel.observeError().observe(this, Observer {
            handleError(it)
        })
        viewModel.observeStartRetrieveData().observe(this, Observer {
            showProgressDialog()
        })
        viewModel.observeFinishRetrieveData().observe(this, Observer {
            dismissProgressDialog()
        })
    }

    private fun updateAlbums(newAlbums: List<Album>) {
        Timber.d("List of albums $newAlbums")
        albums.clear()
        albums.addAll(newAlbums)
        albumsAdapter.notifyDataSetChanged()
        swipeRefresh.isRefreshing = false
    }

    override fun onAlbumSelected(album: Album) {
        val intent = Intent(this, AlbumDetailActivity::class.java)
        intent.putExtra(AlbumDetailActivity.EXTRA_BOOK_ID, album.id)
        startActivity(intent)
    }


}
