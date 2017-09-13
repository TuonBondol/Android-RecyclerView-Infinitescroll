package nanita.entr.android.model.response

/***
 *
 * @author TUON BONDOL
 *
 */

data class HomeResponse (val Foods: ArrayList<Foods>, var Response: Response)

data class Foods(var loadingStatus: Boolean = false, val FoodId:Int = 0, val Name:String = "", val ImageUrl: String = "")