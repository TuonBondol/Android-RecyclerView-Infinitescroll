# Android-RecyclerView-Infinitescroll
This sample app with showing the way how to implement infinite scroll in Android RecyclerView

#  Android-RecyclerView-Infinitescroll

## Getting Started
This sample app with showing the way how to implement infinite scroll in Android RecyclerView

## Installing

We can install this library by using gradle

Step 1

```
allprojects {
	repositories {
			maven { url 'https://jitpack.io' }
		}
	}
```

Step 2

```
dependencies {
	         compile 'com.github.tuonbondol:Android-RecyclerView-Infinitescroll:1.0.5'
	}
```

## Sample Using

```
class HomeRecyclerViewAdapter(val mContext: Context, mRecyclerView: RecyclerView, val mLayoutManager: LinearLayoutManager,
                              mRecyclerViewAdapterCallback: InfiniteScrollRecyclerView.RecyclerViewAdapterCallback, var mDataList: ArrayList<Foods>?, val mItemClickCallback: HomeItemClick?)
    : RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder>() {

    val holderLoading: Int = 0
    val holderRow: Int = 1
    var mInfiniteScrollRecyclerView: InfiniteScrollRecyclerView? = null

    init {
        mInfiniteScrollRecyclerView = InfiniteScrollRecyclerView(mContext, mRecyclerView, mLayoutManager, mRecyclerViewAdapterCallback)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        if (viewType == holderRow) {
            return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.my_lesson_row_layout, parent, false))
        }
        return ViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.infinite_loading_progress_bar_layout, parent, false))
    }

    override fun getItemCount(): Int = mDataList!!.size

    override fun getItemViewType(position: Int): Int {
        when (mDataList!![position].loadingStatus) {
            false -> return holderRow
            else -> {
                return holderLoading
            }
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
}

```

```
fun onSetUpRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        rvListData.layoutManager = layoutManager
        homeAdapter = HomeRecyclerViewAdapter(mContext = this,
                mRecyclerView = rvListData,
                mLayoutManager = layoutManager,
                mRecyclerViewAdapterCallback = this,
                mDataList = foodData!!,
                mItemClickCallback = this)
        rvListData.adapter = homeAdapter
        homeAdapter?.setLoadingStatus(true)
    }
    
```

## Min SDK Version

```
Support Min Sdk version >= 16

```

## Authors

* **Bondol Tuon** - [Bondol Tuon](https://github.com/BondolTuon)

See also the list of [contributors](https://github.com/BondolTuon/Android-RecyclerView-Infinitescroll/graphs/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](https://github.com/BondolTuon/Android-RecyclerView-Infinitescroll/blob/master/README.md) file for details
