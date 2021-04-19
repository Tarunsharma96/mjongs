package com.mp.mj.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.mp.mj.databinding.LayoutFragmentSongDetailBinding
import com.squareup.picasso.Picasso

class SongDetailFragment : Fragment(){

    private lateinit var layoutFragmentSongDetailBinding: LayoutFragmentSongDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        layoutFragmentSongDetailBinding = LayoutFragmentSongDetailBinding.inflate(inflater,container,false)

        return layoutFragmentSongDetailBinding.root
    }

    override fun onViewCreated(@NonNull view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null){

            layoutFragmentSongDetailBinding.detailSongTitleText.text = requireArguments().getString("song")
            layoutFragmentSongDetailBinding.detailAlbumTitleText.text = requireArguments().getString("album")
            layoutFragmentSongDetailBinding.detailSongArtistText.text = requireArguments().getString("artist")
            layoutFragmentSongDetailBinding.detailSongReleasedText.text = requireArguments().getString("date")
            layoutFragmentSongDetailBinding.detailSongGenreText.text = requireArguments().getString("genre")
            layoutFragmentSongDetailBinding.detailSongTimeText.text = requireArguments().getString("trackTime")

            Picasso.get().load(requireArguments().getString("img")).into(layoutFragmentSongDetailBinding.detailAlbumImg)
        }

    }



}