package nanita.entr.android.utils

import android.content.Context
import android.net.ConnectivityManager

/****
 *
 * @author TUON BONDOL Date: 9/1/17.
 *
 */

fun isNetworkConnected(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = connectivityManager.activeNetworkInfo
    return netInfo != null && netInfo.isConnected
}