package speedev.android.recyclerviewinfinitescroll.home

import speedev.android.recyclerviewinfinitescroll.BaseModel
import speedev.android.recyclerviewinfinitescroll.BasePresenter
import speedev.android.recyclerviewinfinitescroll.BaseView
import speedev.android.recyclerviewinfinitescroll.model.response.HomeResponse

/****
 *
 * @author TUON BONDOL
 *
 */

interface HomeInterface {

    interface View : BaseView<Presenter> {
        fun homeResponseSuccess(homeData: HomeResponse)
        fun homeResponseInfiniteSuccess(homeData: HomeResponse)
        fun responseError(errorObjects: Any)
    }

    interface Presenter : BasePresenter {
        fun requestDataFromServer(infiniteStatus: Boolean)
    }

    interface Model : BaseModel {
        fun homeResponseSuccess(homeData: HomeResponse)
        fun homeResponseInfiniteSuccess(homeData: HomeResponse)
    }
}