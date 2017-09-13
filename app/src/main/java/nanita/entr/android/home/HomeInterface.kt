package nanita.entr.android.home

import nanita.entr.android.BaseModel
import nanita.entr.android.BasePresenter
import nanita.entr.android.BaseView
import nanita.entr.android.model.response.HomeResponse

/***
 *
 * @author Nanita Tech on 9/14/2017.
 *
 */

interface HomeInterface {

    interface View : BaseView<Presenter>{
        fun HomeResponseSuccess(homeData:HomeResponse)
        fun HomeResponseInfiniteSuccess(homeData:HomeResponse)
        fun responseError(errorObjects: Any)
    }

    interface Presenter : BasePresenter{
        fun requestDataFromServer(infiniteStatus: Boolean)
    }

    interface Model : BaseModel{
        fun HomeResponseSuccess(homeData:HomeResponse)
        fun HomeResponseInfiniteSuccess(homeData:HomeResponse)
    }
}