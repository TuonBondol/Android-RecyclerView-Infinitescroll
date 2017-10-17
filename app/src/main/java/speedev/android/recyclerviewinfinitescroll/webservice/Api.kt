package speedev.android.recyclerviewinfinitescroll.webservice

import io.reactivex.Observable
import speedev.android.recyclerviewinfinitescroll.model.response.HomeResponse
import retrofit2.http.GET

/****
 *
 * @author TUON BONDOL Date: 9/1/17.
 *
 */

interface Api {
    @GET("foods")
    fun homeListFoods(): Observable<HomeResponse>
}