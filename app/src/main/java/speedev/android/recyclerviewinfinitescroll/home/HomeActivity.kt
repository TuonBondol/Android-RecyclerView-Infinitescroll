package speedev.android.recyclerviewinfinitescroll.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.tuonbondol.recyclerviewinfinitescroll.InfiniteScrollRecyclerView
import speedev.android.recyclerviewinfinitescroll.BaseActivity
import kotlinx.android.synthetic.main.default_home_toolbar_layout.*
import kotlinx.android.synthetic.main.default_recycler_view_layout.*
import speedev.android.recyclerviewinfinitescroll.model.response.Foods
import speedev.android.recyclerviewinfinitescroll.model.response.HomeResponse
import org.jetbrains.anko.toast
import speedev.android.recyclerviewinfinitescroll.R

/****
 *
 * @author TUON BONDOL
 *
 */

class HomeActivity : BaseActivity(), HomeInterface.View, InfiniteScrollRecyclerView.RecyclerViewAdapterCallback, HomeRecyclerViewAdapter.HomeItemClick {

    private var homeAdapter: HomeRecyclerViewAdapter? = null
    private var mHomePresenter: HomeInterface.Presenter? = null
    private var foodData: ArrayList<Foods>? = ArrayList<Foods>()
    private val mLoadingData = Foods(loadingStatus = true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onSetUpHomeToolbar(resources.getString(R.string.home))

        setPresenter(HomePresenter(this))
    }

    private fun onSetUpHomeToolbar(toolbarTitle: String) {
        setSupportActionBar(tbHome)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        tvHomeTitle.text = toolbarTitle
    }

    override fun setPresenter(presenter: HomeInterface.Presenter) {
        mHomePresenter = presenter
        mHomePresenter?.requestDataFromServer(false)
    }

    override fun homeResponseSuccess(homeData: HomeResponse) {
        foodData = homeData.Foods
        onSetUpRecyclerView()
    }

    override fun homeResponseInfiniteSuccess(homeData: HomeResponse) {
        homeAdapter?.setLoadingStatus(true)
        foodData!!.removeAt(foodData!!.size - 1)
        foodData?.addAll(homeData.Foods)
        homeAdapter?.notifyDataSetChanged()
    }

    override fun responseError(errorObjects: Any) {
        toast("Response Error $errorObjects")
        homeAdapter?.setLoadingStatus(true)
    }

    override fun onLoadMoreData() {
        foodData?.add(mLoadingData)
        homeAdapter?.notifyDataSetChanged()
        mHomePresenter?.requestDataFromServer(true)
    }

    private fun onSetUpRecyclerView() {
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

    override fun itemClickCallback(position: Int) {
        toast("Item Clicked Position $position")
    }
}