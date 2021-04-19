package com.mp.mj.ui.main

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.mp.mj.R
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*


class MjSongsAdapter(private var albumDataList: List<Album>, private val mjSongDetailListener: MjSongDetailListener) : RecyclerView.Adapter<MjSongsAdapter.MyCartViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MjSongsAdapter.MyCartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.layout_mj_songs_item,
                parent,
                false
        )
        return MyCartViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: MjSongsAdapter.MyCartViewHolder, position: Int) {
        val albumDataItem = albumDataList[position]
        holder.songTitleText.text = albumDataItem.songName
        holder.songGenreTitleText.text = albumDataItem.songGenre
        holder.albumTitleText.text = albumDataItem.albumName

        val imgUrl = albumDataItem.img
        Picasso.get().load(imgUrl).into(holder.albumImage)

        holder.trackTimeText.text = getTrackTime(albumDataItem.trackTime.toLong())

        holder.constraintLayout.setOnClickListener{
            mjSongDetailListener.onItemClick(albumDataItem.songName, albumDataItem.albumName,
                    albumDataItem.songArtist, albumDataItem.songGenre, albumDataItem.img, getReleasedYear(albumDataItem.releasedDate),getTrackTime(albumDataItem.trackTime?.toLong()))
        }
    }

    override fun getItemCount(): Int {
        return albumDataList.size
    }

    inner class MyCartViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var constraintLayout: ConstraintLayout = view.findViewById(R.id.item_constraint_layout)
        var songTitleText: TextView = view.findViewById(R.id.song_title_text)
        var songGenreTitleText: TextView = view.findViewById(R.id.genre_title_text)
        var albumTitleText: TextView = view.findViewById(R.id.album_title_text)
        var albumImage: ImageView = view.findViewById(R.id.song_img)
        var trackTimeText: TextView = view.findViewById(R.id.song_time_text)

    }
    @SuppressLint("SimpleDateFormat")
    private fun getReleasedYear(releasedYear: String): String {
        val sd = SimpleDateFormat("yyyy-mm-dd")
        val calendar: Calendar = GregorianCalendar()
        calendar.time = sd.parse(releasedYear)
        return calendar.get(Calendar.YEAR).toString()
    }

    private fun getTrackTime(trackTime: Long): String {
        val minutes: Long = (trackTime) / 1000 / 60
        val seconds: Long = (trackTime) / 1000 % 60
        return ("$minutes.$seconds")
    }
}