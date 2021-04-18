package com.example.sportapp

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridMenuItemDecorator(
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
        outRect.bottom = sizeGridSpacing
        outRect.right = sizeGridSpacing

        val itemPosition = (view.layoutParams as RecyclerView.LayoutParams).viewAdapterPosition
        if (itemPosition < numberOfColumn) {
            outRect.top = sizeGridSpacing
        }
        else {
            outRect.top = 0
        }
        if (itemPosition % numberOfColumn == 0) {
            outRect.left = sizeGridSpacing
        }
        else {
            outRect.right = sizeGridSpacing
        }
    }
}