package speedev.android.recyclerviewinfinitescroll.model.request

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import speedev.android.recyclerviewinfinitescroll.home.HomeInterface
import speedev.android.recyclerviewinfinitescroll.model.response.HomeResponse
import speedev.android.recyclerviewinfinitescroll.webservice.Webservice

/****
 *
 * @author TUON BONDOL
 *
 */

class HomeRequest(private val infiniteStatus: Boolean) {

    private var mHomeInterface: HomeInterface.Model? = null
    private var mCompositeDisposable: CompositeDisposable? = null

    fun homeDataWithServer(mTopInterface: HomeInterface.Model) {
        this.mHomeInterface = mTopInterface
        mCompositeDisposable = CompositeDisposable()

        mCompositeDisposable?.add(Webservice.requestService().homeListFoods()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::successResponse, this::errorResponse))
    }

    private fun successResponse(mHomeReponse: HomeResponse) {
        mCompositeDisposable?.dispose()
        if (infiniteStatus) {
            mHomeInterface?.homeResponseInfiniteSuccess(mHomeReponse)
            return
        }
        mHomeInterface?.homeResponseSuccess(mHomeReponse)
    }

    private fun errorResponse(error: Throwable) {
        mCompositeDisposable?.dispose()
        mHomeInterface?.responseError(error)
    }
}