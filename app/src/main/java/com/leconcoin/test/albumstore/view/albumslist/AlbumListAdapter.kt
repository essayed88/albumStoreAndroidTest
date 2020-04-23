package com.leconcoin.test.albumstore.view.albumslist


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.leconcoin.test.albumstore.R
import com.squareup.picasso.Picasso
import com.leconcoin.test.albumstore.model.Album

class AlbumsListAdapter(
    private val albums: List<Album>,
    private val listener: AlbumsListAdapterListener?
) : RecyclerView.Adapter<AlbumsListAdapter.ViewHolder>(), View.OnClickListener {

    interface AlbumsListAdapterListener {
        fun onAlbumSelected(album: Album)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView = itemView.findViewById<CardView>(R.id.cardView)!!
        val albumCover = itemView.findViewById<ImageView>(R.id.albumCover)!!
        val albumTitle = itemView.findViewById<TextView>(R.id.albumTitle)!!
        val albumId = itemView.findViewById<TextView>(R.id.albumId)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_album, parent, false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = albums[position]
        with(holder) {
            cardView.setOnClickListener(this@AlbumsListAdapter)
            cardView.tag = album
            albumTitle.text = album.title
            albumId.text = album.albumId.toString()

            Picasso.get()
                .load(album.thumbnailUrl)
                .placeholder(R.drawable.ic_placeholder_image)
                .into(albumCover)
        }

    }

    override fun getItemCount() = albums.size

    override fun onClick(view: View) {
        when (view.id) {
            R.id.cardView -> listener?.onAlbumSelected(view.tag as Album)
        }
    }

}