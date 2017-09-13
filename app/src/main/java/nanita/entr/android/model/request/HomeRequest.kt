package nanita.entr.android.model.request

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import nanita.entr.android.home.HomeInterface
import nanita.entr.android.model.response.HomeResponse
import nanita.entr.android.webservice.API
import nanita.entr.android.webservice.Webservice

/***
 *
 * @author Nanita Tech on 9/14/2017.
 *
 */

class HomeRequest(val infiniteStatus: Boolean) {

    private var mHomeInterface: HomeInterface.Model? = null
    private var mCompositeDisposable: CompositeDisposable? = null

    fun homeDataWithServer(mTopInterface: HomeInterface.Model) {
        this.mHomeInterface = mTopInterface
        mCompositeDisposable = CompositeDisposable()
        val mAPI = Webservice.mInstnaceWebService.WebServiceRequestMockUp(API::class.java)
        mCompositeDisposable?.add(mAPI.homeListFoods()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::successResponse, this::errorResponse))
    }

    private fun successResponse(mHomeReponse:HomeResponse) {
        mCompositeDisposable?.dispose()
        if(infiniteStatus){
            mHomeInterface?.HomeResponseInfiniteSuccess(mHomeReponse)
            return
        }
        mHomeInterface?.HomeResponseSuccess(mHomeReponse)
    }

    private fun errorResponse(error: Throwable) {
        mCompositeDisposable?.dispose()
        mHomeInterface?.responseError(error)
    }
}