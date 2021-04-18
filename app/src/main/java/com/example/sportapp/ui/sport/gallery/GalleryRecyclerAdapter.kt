package com.example.sportapp.ui.sport.gallery

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportapp.databinding.GalleryGridItemBinding

class GalleryRecyclerAdapter(private val images: List<Drawable>): RecyclerView.Adapter<GalleryRecyclerAdapter.GalleryImageViewHolder>() {

    class GalleryImageViewHolder(val binding: GalleryGridItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryImageViewHolder {
        val view = GalleryGridItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryImageViewHolder, position: Int) {
        holder.binding.galleryImage.setImageDrawable(images[position])
    }

    override fun getItemCount(): Int = images.size


}