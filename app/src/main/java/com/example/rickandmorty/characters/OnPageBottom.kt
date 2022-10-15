package com.example.rickandmorty.characters

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OnPageBottom {

    var page: Int = 1

    fun getOnPageBottom(recyclerView: RecyclerView, action: AddGetDataFunction) {
        if (page == 1) {
            action.getData(page)
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

                val lastPosVisible: Int =
                    (recyclerView.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                val allItemCount: Int = (recyclerView.layoutManager as GridLayoutManager).itemCount

                Log.i("PositionNumber", lastPosVisible.toString())
                Log.i("AllItemsNumber", allItemCount.toString())
                if (lastPosVisible in (allItemCount - 4)..allItemCount) {
                    page++
                    action.getData(page)
                }
            }
        })
    }
}

interface AddGetDataFunction {
    fun getData(page: Int)
}

