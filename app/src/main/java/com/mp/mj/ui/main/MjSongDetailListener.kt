package com.mp.mj.ui.main

interface MjSongDetailListener {

    fun onItemClick(
            songName: String,
            collectionName: String,
            songArtist: String,
            songGenre: String,
            songImg: String,
            songReleased: String,
            trackTime: String,
    )
}