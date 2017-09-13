package nanita.entr.android.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.tuonbondol.recyclerviewinfinitescroll.InfiniteScrollRecyclerView
import nanita.entr.android.BaseActivity
import core.mvp.kotlinandroid.R
import kotlinx.android.synthetic.main.default_home_toolbar_layout.*
import kotlinx.android.synthetic.main.default_recycler_view_layout.*
import nanita.entr.android.model.response.Foods
import nanita.entr.android.model.response.HomeResponse
import org.jetbrains.anko.toast

/****
 *
 * @author TUON BONDOL Date: 9/1/17.
 *
 */

class HomeActivity : BaseActivity(), HomeInterface.View, InfiniteScrollRecyclerView.RecyclerViewAdapterCallback, HomeRecyclerViewAdapter.HomeItemClick {
    override fun responseError(errorObjects: Any) {
        homeAdapter?.setLoadingStatus(true)
    }

    override fun HomeResponseInfiniteSuccess(homeData: HomeResponse) {
        homeAdapter?.setLoadingStatus(true)
        foodData!!.removeAt(foodData!!.size - 1)
        foodData?.addAll(homeData.Foods)
        homeAdapter?.notifyDataSetChanged()
    }

    val mLoadingData = Foods(loadingStatus = true)

    override fun ItemClickCallback(position: Int) {
        toast("Item Clicked Position $position")
    }

    override fun onLoadMoreData() {
        foodData?.add(mLoadingData)
        homeAdapter?.notifyDataSetChanged()
        mHomePresenter?.requestDataFromServer(true)
    }

    var homeAdapter: HomeRecyclerViewAdapter? = null
    var mHomePresenter: HomeInterface.Presenter? = null
    var foodData: ArrayList<Foods>? = ArrayList<Foods>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onSetUpHomeToolbar(resources.getString(R.string.home))

        onSetUpRecyclerView()

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

    override fun HomeResponseSuccess(homeData: HomeResponse) {
        foodData = homeData.Foods
        homeAdapter?.onUpdateData(foodData)
    }

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
}