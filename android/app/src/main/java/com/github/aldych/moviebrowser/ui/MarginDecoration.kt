package com.github.aldych.moviebrowser.ui

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class MarginDecoration(private val margin: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        outRect.set(margin, margin, margin, margin)
    }
}