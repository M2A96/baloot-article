package com.example.ataei.util

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager


abstract class EndlessRecyclerViewScrollListener : RecyclerView.OnScrollListener {
    var oldY = 0

    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private var visibleThreshold = 1

    // The current offset index of data you have loaded
    private var currentPage = 0

    // The total number of items in the dataset after the last load
    private var previousTotalItemCount = 0

    // True if we are still waiting for the last set of data to load.
    var loading = true

    // Sets the starting page index
    private val startingPageIndex = 0

    var layoutManager: RecyclerView.LayoutManager? = null
        set(value) {
            field = value
            if (value is GridLayoutManager) {
                visibleThreshold *= value.spanCount
            } else if (value is StaggeredGridLayoutManager) {
                visibleThreshold *= value.spanCount
            }
            this.previousTotalItemCount = 0
        }

    constructor() {}

    constructor(layoutManager: RecyclerView.LayoutManager) {
        this.layoutManager = layoutManager
    }


    fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

    // This happens many times a second during a scroll, so be wary of the code you place here.
    // We are given a few useful parameters to help us work out if we need to load some more data,
    // but first we check if we are waiting for the previous load to finish.
    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        if (dy < 0)
            return
        var lastVisibleItemPosition = 0
        val totalItemCount = layoutManager!!.itemCount

        // This condition will useful when recyclerview has less than visibleThreshold items
        when (layoutManager) {
            is StaggeredGridLayoutManager -> {
                val lastVisibleItemPositions =
                    (layoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(null)
                // get maximum element within the list
                lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)
            }
            is GridLayoutManager -> lastVisibleItemPosition =
                (layoutManager as GridLayoutManager).findLastVisibleItemPosition()
            is LinearLayoutManager -> lastVisibleItemPosition =
                (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
        }

        // If it’s still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        // threshold should reflect how many total columns there are too

        // If it’s still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
        if (loading && totalItemCount > previousTotalItemCount) {
            previousTotalItemCount = totalItemCount
        }
        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        // threshold should reflect how many total columns there are too
        if (!loading && lastVisibleItemPosition + visibleThreshold >= totalItemCount
            && view.adapter!!.itemCount > visibleThreshold
        ) {// This condition will useful when recyclerview has less than visibleThreshold items
            currentPage++
            onLoadMore(currentPage, totalItemCount, view)
        }
    }

    // Call whenever performing new searches
    fun resetState() {
        this.currentPage = this.startingPageIndex
        this.previousTotalItemCount = 0
        this.loading = true
    }

    fun setCurrentPage(currentPage: Int) {
        this.currentPage = currentPage
    }


    // Defines the process for actually loading more data based on page
    abstract fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView)

}