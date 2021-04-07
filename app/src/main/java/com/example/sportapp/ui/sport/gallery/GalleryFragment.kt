package com.example.sportapp.ui.sport.gallery

import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportapp.*
import com.example.sportapp.databinding.FragmentGalleryBinding
import java.util.*

class GalleryFragment : Fragment() {
    private lateinit var sportGame: SportsGamesTypes
    private val images = mutableListOf<Drawable>()
    private lateinit var adapter: GalleryRecyclerAdapter
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    private val numberOfGridColumns = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sportGame = arguments?.getSerializable(getString(R.string.sportTypeKey)) as SportsGamesTypes

        setImages()
        adapter = GalleryRecyclerAdapter(images)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)

        activity?.title = "Галерея (${sportGame.russianName.toLowerCase(Locale.getDefault())})"

        binding.galleryRecyclerView.layoutManager = GridLayoutManager(activity, numberOfGridColumns, LinearLayoutManager.VERTICAL, false)
        binding.galleryRecyclerView.addItemDecoration(GridImagesItemDecorator(10, numberOfGridColumns))
        binding.galleryRecyclerView.adapter = adapter

        return binding.root
    }

    private fun setImages() {
        val drawables: TypedArray = when (sportGame) {
            SportsGamesTypes.FOOTBALL -> {
                resources.obtainTypedArray(R.array.gallery_football)
            }
            SportsGamesTypes.BASKETBALL -> {
                resources.obtainTypedArray(R.array.gallery_basketball)
            }
            SportsGamesTypes.TENNIS -> {
                resources.obtainTypedArray(R.array.gallery_tennis)
            }
            SportsGamesTypes.HANDBALL -> {
                resources.obtainTypedArray(R.array.gallery_handball)
            }
        }

        for (i in 0 until drawables.length()) {
            images.add(drawables.getDrawable(i)!!)
        }

        drawables.recycle()
    }
}