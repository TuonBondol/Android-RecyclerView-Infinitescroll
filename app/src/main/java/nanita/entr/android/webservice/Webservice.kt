package nanita.entr.android.webservice

import nanita.entr.android.model.response.ErrorMessage
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/***
 *
 * @author TUON BONDOL
 *
 */

class Webservice() {

    val baseLiveUrl: String = ""
    val baseLocalUrl: String = ""
    val baseUrlMockUp: String = "https://private-4f332-androidrecyclerviewinfinitescroll.apiary-mock.com/"

    val mContentType: String = "Content-Type"
    val mContentTypeValue: String = "application/json"

    val mAuthorizationType: String = "Authorization"
    val mAuthorizationValue: String = "Bearer "

    val mUserToken: String = "token"

    private object WebServiceInstance {
        val mWebServiceInstance = Webservice()
    }

    companion object WebServiceFactory {
        val mInstnaceWebService: Webservice by lazy { WebServiceInstance.mWebServiceInstance }
    }

    private fun customOkHttpClient(): OkHttpClient {
        try {
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}

                override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> = arrayOf()
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { hostname, session -> true }

            return builder.build()

        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    fun <S> WebServiceRequestLive(myServiceModel: Class<S>): S {
        val mOkHttpClientBuilder = customOkHttpClient().newBuilder()

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
                .baseUrl(baseLiveUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build()
                .create(myServiceModel)
    }

    fun <S> WebServiceRequestLive(myServiceModel: Class<S>, authorization: String): S {
        val mOkHttpClientBuilder = customOkHttpClient().newBuilder()

        val mOkHttpClient = mOkHttpClientBuilder
                .addInterceptor({ chain ->
                    var mRequest = chain.request()

                    mRequest = mRequest.newBuilder()
                            .header(mContentType, mContentTypeValue)
                            .header(mAuthorizationType, mAuthorizationValue + authorization)
                            .build()

                    chain.proceed(mRequest)
                })
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
                .baseUrl(baseLiveUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build()
                .create(myServiceModel)
    }

    fun <S> WebServiceRequestLive(myServiceModel: Class<S>, authorization: String, token: String): S {
        val mOkHttpClientBuilder = customOkHttpClient().newBuilder()

        val mOkHttpClient = mOkHttpClientBuilder
                .addInterceptor({ chain ->
                    var mRequest = chain.request()

                    mRequest = mRequest.newBuilder()
                            .header(mContentType, mContentTypeValue)
                            .header(mAuthorizationType, mAuthorizationValue + authorization)
                            .header(mUserToken, token)
                            .build()
                    chain.proceed(mRequest)
                })
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
                .baseUrl(baseLiveUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build()
                .create(myServiceModel)
    }

    fun <S> WebServiceRequestLocal(myServiceModel: Class<S>): S {
        val mOkHttpClientBuilder = customOkHttpClient().newBuilder()

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
                .baseUrl(baseLocalUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build()
                .create(myServiceModel)
    }

    fun <S> WebServiceRequestLocal(myServiceModel: Class<S>, authorization: String): S {
        val mOkHttpClientBuilder = customOkHttpClient().newBuilder()

        val mOkHttpClient = mOkHttpClientBuilder
                .addInterceptor({ chain ->
                    var mRequest = chain.request()

                    mRequest = mRequest.newBuilder()
                            .header(mContentType, mContentTypeValue)
                            .header(mAuthorizationType, mAuthorizationValue + authorization)
                            .build()

                    chain.proceed(mRequest)
                })
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
                .baseUrl(baseLocalUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build()
                .create(myServiceModel)
    }

    fun <S> WebServiceRequestLocal(myServiceModel: Class<S>, authorization: String, token: String): S {
        val mOkHttpClientBuilder = customOkHttpClient().newBuilder()

        val mOkHttpClient = mOkHttpClientBuilder
                .addInterceptor({ chain ->
                    var mRequest = chain.request()

                    mRequest = mRequest.newBuilder()
                            .header(mContentType, mContentTypeValue)
                            .header(mAuthorizationType, mAuthorizationValue + authorization)
                            .header(mUserToken, token)
                            .build()

                    chain.proceed(mRequest)
                })
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
                .baseUrl(baseLocalUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build()
                .create(myServiceModel)
    }

    fun <S> WebServiceRequestMockUp(myServiceModel: Class<S>): S {
        val mOkHttpClientBuilder = customOkHttpClient().newBuilder()

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
                .baseUrl(baseUrlMockUp)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build()
                .create(myServiceModel)
    }

    fun <S> WebServiceRequestMockUp(myServiceModel: Class<S>, authorization: String): S {
        val mOkHttpClientBuilder = customOkHttpClient().newBuilder()

        val mOkHttpClient = mOkHttpClientBuilder
                .addInterceptor({ chain ->
                    var mRequest = chain.request()

                    mRequest = mRequest.newBuilder()
                            .header(mContentType, mContentTypeValue)
                            .header(mAuthorizationType, mAuthorizationValue + authorization)
                            .build()

                    chain.proceed(mRequest)
                })
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
                .baseUrl(baseUrlMockUp)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build()
                .create(myServiceModel)
    }

    fun <S> WebServiceRequestMockUp(myServiceModel: Class<S>, authorization: String, token: String): S {
        val mOkHttpClientBuilder = customOkHttpClient().newBuilder()

        val mOkHttpClient = mOkHttpClientBuilder
                .addInterceptor({ chain ->
                    var mRequest = chain.request()

                    mRequest = mRequest.newBuilder()
                            .header(mContentType, mContentTypeValue)
                            .header(mAuthorizationType, mAuthorizationValue + authorization)
                            .header(mUserToken, token)
                            .build()

                    chain.proceed(mRequest)
                })
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
                .baseUrl(baseUrlMockUp)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build()
                .create(myServiceModel)
    }

    fun getXMessage(errorThrowable: Throwable): ErrorMessage {
        var mErrorMessage: ErrorMessage? = null
        if (errorThrowable is HttpException) {
            println(errorThrowable.message())
            try {
                if (errorThrowable.code() != 200) {
                    mErrorMessage = ErrorMessage(errorThrowable.code(), errorThrowable.response().headers().get("XMessage"))
                }
            } catch (e: Exception) {
                mErrorMessage = ErrorMessage(1000, "Can not get XMessage from server!")
                e.printStackTrace()
            }
        } else {
            return ErrorMessage(1000, "Can not get XMessage from server!")
        }
        return mErrorMessage!!
    }
}