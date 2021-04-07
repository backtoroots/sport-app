package com.example.sportapp.ui.sport.gallery

import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportapp.*
import com.example.sportapp.databinding.FragmentGalleryBinding
import com.example.sportapp.databinding.FragmentRulesBinding
import com.example.sportapp.ui.sport.rules.RulesRecyclerAdapter
import java.util.*

class GalleryFragment : Fragment() {
    private lateinit var sportGame: SportsGamesTypes
    private val images = mutableListOf<Drawable>()
    lateinit var adapter: GalleryRecyclerAdapter
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            val sportName = it.getString(getString(R.string.sportKeyName))
            sportGame = enumValueOf(sportName!!)
        }

        setImages()
        adapter = GalleryRecyclerAdapter(images)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)

        activity?.title = "Галерея (${sportGame?.russianName?.toLowerCase(Locale.getDefault())})"

        binding.galleryRecyclerView.layoutManager = GridLayoutManager(activity, 3, LinearLayoutManager.VERTICAL, false)
        binding.galleryRecyclerView.addItemDecoration(VerticalSpacingItemDecorator(10))
        binding.galleryRecyclerView.adapter = adapter

        return binding.root
    }

    fun setImages() {
        lateinit var drawables: TypedArray
        when (sportGame) {
            SportsGamesTypes.FOOTBALL -> {
                drawables = resources.obtainTypedArray(R.array.gallery_football)
            }
            SportsGamesTypes.BASKETBALL -> {
                drawables = resources.obtainTypedArray(R.array.gallery_basketball)
            }
            SportsGamesTypes.TENNIS -> {
                drawables = resources.obtainTypedArray(R.array.gallery_football) // TODO add photos and change
            }
            SportsGamesTypes.HANDBALL -> {
                drawables = resources.obtainTypedArray(R.array.gallery_football) // TODO add photos and change
            }
        }

        drawables.length()

        for (i in 0 until drawables.length()) {
            images.add(drawables.getDrawable(i)!!)
        }
    }
}