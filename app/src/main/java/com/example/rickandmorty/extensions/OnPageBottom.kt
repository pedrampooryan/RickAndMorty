package com.example.rickandmorty.extensions

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.onBottomReached(action: () -> Unit) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

            val lastPosVisible: Int =
                (recyclerView.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
            val allItemCount: Int = (recyclerView.layoutManager as GridLayoutManager).itemCount

            Log.i("PositionNumber", lastPosVisible.toString())
            Log.i("AllItemsNumber", allItemCount.toString())
            if (lastPosVisible in (allItemCount - 4)..allItemCount) {
                action()
            }
        }
    })
}