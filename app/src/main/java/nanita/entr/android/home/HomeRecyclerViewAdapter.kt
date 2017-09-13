package nanita.entr.android.home

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.tuonbondol.recyclerviewinfinitescroll.InfiniteScrollRecyclerView
import core.mvp.kotlinandroid.R
import kotlinx.android.synthetic.main.my_lesson_row_layout.view.*
import nanita.entr.android.model.response.Foods

/***
 *
 * @author Nanita Tech on 9/14/2017.
 *
 */

class HomeRecyclerViewAdapter(val mContext: Context, mRecyclerView: RecyclerView, val mLayoutManager: LinearLayoutManager,
                              mRecyclerViewAdapterCallback: InfiniteScrollRecyclerView.RecyclerViewAdapterCallback, var mDataList: ArrayList<Foods>?, val mItemClickCallback: HomeItemClick?)
    : RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder>() {

    val holderLoading: Int = 0
    val holderRow: Int = 1
    var mInfiniteScrollRecyclerView: InfiniteScrollRecyclerView? = null

    init {
        mInfiniteScrollRecyclerView = InfiniteScrollRecyclerView(mContext, mRecyclerView, mLayoutManager, mRecyclerViewAdapterCallback)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (holder?.itemViewType == holderRow) {
            holder.bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        when (mDataList!![position].loadingStatus) {
            false -> return holderRow
            else -> {
                return holderLoading
            }
        }
    }

    override fun getItemCount(): Int = mDataList!!.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        if (viewType == holderRow) {
            return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.my_lesson_row_layout, null))
        }
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.infinite_loading_progress_bar_layout, null))
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            Glide.with(mContext).load(mDataList!![adapterPosition].ImageUrl).into(itemView.ivFoodProfile)

            itemView.setOnClickListener {
                mItemClickCallback?.let {
                    mItemClickCallback.ItemClickCallback(adapterPosition)
                }
            }
        }
    }

    interface HomeItemClick {
        fun ItemClickCallback(position: Int)
    }

    fun setLoadingStatus(status: Boolean) {
        mInfiniteScrollRecyclerView!!.setLoadingStatus(status)
    }

    fun onUpdateData(listData: ArrayList<Foods>?) {
        mDataList = listData
        notifyDataSetChanged()
    }
}