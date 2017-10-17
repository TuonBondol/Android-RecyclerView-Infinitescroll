package speedev.android.recyclerviewinfinitescroll.webservice

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/****
 *
 * @author TUON BONDOL
 *
 */

object Webservice {
    private const val baseUrl: String = "https://private-4f332-androidrecyclerviewinfinitescroll.apiary-mock.com/"

    private const val mContentType: String = "Content-Type"
    private const val mContentTypeValue: String = "application/json"

    fun requestService(): Api {
        val mOkHttpClientBuilder = OkHttpClient().newBuilder()

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val mOkHttpClient = mOkHttpClientBuilder
                .addInterceptor({ chain ->
                    var mRequest = chain.request()
                    mRequest = mRequest.newBuilder()
                            .header(mContentType, mContentTypeValue)
                            .build()

                    chain.proceed(mRequest)
                })
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build()
                .create(Api::class.java)
    }
}