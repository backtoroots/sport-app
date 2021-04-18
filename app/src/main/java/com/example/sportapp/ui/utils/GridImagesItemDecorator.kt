package com.example.sportapp.ui.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridImagesItemDecorator(
    private val sizeGridSpacing: Int,
    private val numberOfColumn: Int
) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemPosition = (view.layoutParams as RecyclerView.LayoutParams).viewAdapterPosition
        if (itemPosition >= numberOfColumn) {
            outRect.top = sizeGridSpacing
        } else {
            outRect.top = 0
        }
        if ((itemPosition + 1) % numberOfColumn == 0) {
            outRect.right = 0
        } else {
            outRect.right = sizeGridSpacing
        }
    }
}