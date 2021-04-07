package com.example.sportapp.ui.sport.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sportapp.R
import com.example.sportapp.SportsGamesTypes
import java.util.*

class GalleryFragment : Fragment() {
    private lateinit var sportGame: SportsGamesTypes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            val sportName = it.getString(getString(R.string.sportKeyName))
            sportGame = enumValueOf(sportName!!)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.title = "Галерея (${sportGame?.russianName?.toLowerCase(Locale.getDefault())})"
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

}