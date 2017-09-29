package org.ortynskyi.movier.view

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

abstract class EndlessScrollListener(private val linearLayoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    // The minimum amount of items to have below your current scroll position before loading more.
    private val VISIBLE_THRESHOLD = 4
    private val SINGLE_PAGE = 2

    // True if we are still waiting for the last set of data to load.
    private var loading = true
    private var lastPage = false
    // The total number of items in the data set after the last load
    private var previousTotal = 0
    private var currentPage = 1

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = recyclerView!!.childCount
        val totalItemCount = linearLayoutManager.itemCount
        val firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition()
        lastPage = true
        if (loading && totalItemCount > previousTotal) {
            loading = false
            lastPage = false
            previousTotal = totalItemCount
        }
        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + VISIBLE_THRESHOLD) {
            currentPage++
            onLoadMore(currentPage)
            loading = true
            lastPage = false
        }
        if (currentPage == SINGLE_PAGE && loading && !lastPage) {
            lastPage = true
        }
    }

    fun resetPage() {
        this.loading = true
        this.lastPage = false
        this.previousTotal = 0
        this.currentPage = 1
    }

    fun setPage(page: Int) {
        this.currentPage = page
    }

    fun isLastPage(): Boolean {
        return lastPage
    }

    abstract fun onLoadMore(currentPage: Int)
}