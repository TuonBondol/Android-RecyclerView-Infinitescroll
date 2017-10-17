package speedev.android.recyclerviewinfinitescroll.home

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.tuonbondol.recyclerviewinfinitescroll.InfiniteScrollRecyclerView
import kotlinx.android.synthetic.main.my_snack_row_layout.view.*
import speedev.android.recyclerviewinfinitescroll.R
import speedev.android.recyclerviewinfinitescroll.model.response.Foods

/****
 *
 * @author TUON BONDOL
 *
 */

class HomeRecyclerViewAdapter(val mContext: Context, mRecyclerView: RecyclerView, val mLayoutManager: LinearLayoutManager,
                              mRecyclerViewAdapterCallback: InfiniteScrollRecyclerView.RecyclerViewAdapterCallback, var mDataList: ArrayList<Foods>?, val mItemClickCallback: HomeItemClick?)
    : RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder>() {

    private val holderLoading: Int = 0
    private val holderRow: Int = 1
    private var mInfiniteScrollRecyclerView: InfiniteScrollRecyclerView? = null

    init {
        mInfiniteScrollRecyclerView = InfiniteScrollRecyclerView(mContext, mRecyclerView, mLayoutManager, mRecyclerViewAdapterCallback)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        if (viewType == holderRow) {
            return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.my_snack_row_layout, parent, false))
        }
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.infinite_loading_progress_bar_layout, parent, false))
    }

    override fun getItemCount(): Int = mDataList!!.size

    override fun getItemViewType(position: Int): Int = when (mDataList!![position].loadingStatus) {
        false -> holderRow
        else -> {
            holderLoading
        }
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (holder?.itemViewType == holderRow) {
            holder.bind()
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            Picasso.with(itemView.context).load(mDataList!![adapterPosition].ImageUrl).into(itemView.ivFoodProfile)

            itemView.setOnClickListener {
                mItemClickCallback?.let {
                    mItemClickCallback.itemClickCallback(adapterPosition)
                }
            }
        }
    }

    interface HomeItemClick {
        fun itemClickCallback(position: Int)
    }

    fun setLoadingStatus(status: Boolean) {
        mInfiniteScrollRecyclerView!!.setLoadingStatus(status)
    }
}