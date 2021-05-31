package com.example.newgithubuser.presentation.ui.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Utility class used to detect last item reached and call function once it reached bottom
 */
class InfiniteScrollListener(val func: () -> Unit, val layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    private var previousTotal = 0
    private var loading = true
    private var visibleThreshold = 5
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy > 0) {
            visibleItemCount = recyclerView.childCount //5
            totalItemCount = layoutManager.itemCount //10
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition() // 3

            if (loading) {
                //scrolled the first time
                if (totalItemCount > previousTotal) {
                    loading = false
                    previousTotal = totalItemCount
                }
            }
            if (
                !loading &&
                (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)
            ) {
                // End has been reached
                func()
                loading = true
            }
        }
    }

}
