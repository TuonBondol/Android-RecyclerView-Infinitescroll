package speedev.android.recyclerviewinfinitescroll.home

import speedev.android.recyclerviewinfinitescroll.model.request.HomeRequest
import speedev.android.recyclerviewinfinitescroll.model.response.HomeResponse

/***
 *
 * @author TUON BONDOL
 *
 */

class HomePresenter(val mView: HomeInterface.View) : HomeInterface.Presenter, HomeInterface.Model {

    override fun requestDataFromServer(infiniteStatus: Boolean) {
        val request = HomeRequest(infiniteStatus)
        request.homeDataWithServer(this)
    }

    override fun responseError(errorObjects: Any) {
        println("Error Response $errorObjects")
    }

    override fun homeResponseSuccess(homeData: HomeResponse) {
        mView.homeResponseSuccess(homeData)
    }

    override fun homeResponseInfiniteSuccess(homeData: HomeResponse) {
        mView.homeResponseInfiniteSuccess(homeData)
    }
}