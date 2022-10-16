package com.example.rickandmorty.characters

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OnPageBottom {

    fun getOnPageBottom(recyclerView: RecyclerView, action: () -> Unit) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
}
