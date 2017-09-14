package nanita.entr.android.home

import nanita.entr.android.model.request.HomeRequest
import nanita.entr.android.model.response.HomeResponse

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

    override fun HomeResponseSuccess(homeData: HomeResponse) {
        mView.HomeResponseSuccess(homeData)
    }

    override fun HomeResponseInfiniteSuccess(homeData: HomeResponse) {
        mView.HomeResponseInfiniteSuccess(homeData)
    }
}