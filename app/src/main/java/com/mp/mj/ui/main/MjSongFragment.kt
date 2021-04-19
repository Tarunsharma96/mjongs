package com.mp.mj.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.gson.Gson
import com.mp.mj.R
import com.mp.mj.databinding.LayoutFragmentMjSongsBinding
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader


class MjSongFragment : Fragment(), MjSongDetailListener {

    private lateinit var layoutFragmentMjSongsBinding: LayoutFragmentMjSongsBinding
    private lateinit var mjSongsAdapter: MjSongsAdapter
    private val albumItemList = ArrayList<Album>()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        layoutFragmentMjSongsBinding = LayoutFragmentMjSongsBinding.inflate(inflater, container, false)

        getJsonData()
        mjSongsAdapter = activity?.let { MjSongsAdapter(albumItemList, this) }!!
        layoutFragmentMjSongsBinding.recyclerviewMjSongs.adapter = mjSongsAdapter
        return layoutFragmentMjSongsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    private fun getJsonData(){
        val inputStream: InputStream = activity?.assets!!.open("MjSong.json")
        val reader: Reader = InputStreamReader(inputStream)
        val mjSongsData: MJSongsData  = Gson().fromJson(reader, MJSongsData::class.java)
        for(data in mjSongsData.results){
            data.trackName?.let {
                Album(songName = it, albumName = data.collectionName,
                    songArtist = data.artistName, songGenre = data.primaryGenreName, img = data.artworkUrl100, trackTime = data.trackTimeMillis.toString(),
                    releasedDate = data.releaseDate)
            }?.let { albumItemList.add(it) }
        }
    }

    override fun onItemClick(songName: String, collectionName: String, songArtist: String, songGenre: String, songImg: String, songReleased: String, trackTime: String) {

        val bundle = bundleOf("song" to songName, "album" to collectionName, "artist" to songArtist, "genre" to songGenre ,"img" to songImg,
                "date" to songReleased,"trackTime" to trackTime)

        navController.navigate(R.id.action_mjSongFragment_to_songDetailFragment,bundle)
    }

}