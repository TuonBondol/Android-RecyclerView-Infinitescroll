package nanita.entr.android.webservice

import io.reactivex.Observable
import nanita.entr.android.model.response.HomeResponse
import retrofit2.http.GET

/****
 *
 * @author TUON BONDOL Date: 9/1/17.
 *
 */

interface API {
    @GET("foods")
    fun homeListFoods(): Observable<HomeResponse>
}