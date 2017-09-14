package com.tuonbondol.recyclerviewinfinitescroll

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import nanita.entr.android.utils.isNetworkConnected
import org.jetbrains.anko.toast

/****
 *
 * @author TUON BONDOL
 *
 */

class InfiniteScrollRecyclerView(val mContext: Context, val mRecyclerView: RecyclerView, val mLayoutManager: LinearLayoutManager, val mRecyclerViewAdapterCallback: RecyclerViewAdapterCallback) {

    var mPastVisibleItems: Int = 0
    var mVisibleItemCount: Int = 0
    var mTotalItemCount: Int = 0
    var mInfiniteLoadingStatus: Boolean = false

    init {
        InfiniteRecyclerView()
    }

    private fun InfiniteRecyclerView() {
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0) {
                    mVisibleItemCount = mRecyclerView.childCount
                    mTotalItemCount = mLayoutManager.itemCount
                    mPastVisibleItems = mLayoutManager.findFirstVisibleItemPosition()
                    if (mInfiniteLoadingStatus) {
                        if (mVisibleItemCount + mPastVisibleItems >= mTotalItemCount) {
                            mInfiniteLoadingStatus = false
                            if (isNetworkConnected(mContext)) {
                                mRecyclerViewAdapterCallback.onLoadMoreData()
                            } else {
                                mInfiniteLoadingStatus = true
                                mContext.toast(mContext.resources.getString(R.string.no_internet_connection))
                            }
                        }
                    }
                }
            }
        })
    }

    fun setLoadingStatus(status: Boolean) {
        mInfiniteLoadingStatus = status
    }

    interface RecyclerViewAdapterCallback {
        fun onLoadMoreData()
    }
}