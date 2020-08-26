package co.zoozle.android.zoozle.data.networks

import com.gaurav.newsapp.networks.ApiInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit

object OkHttpBuilder {


    fun create(): OkHttpClient {

        return OkHttpClient.Builder().apply {
            addInterceptor {
                val original = it.request()
                val request = original.newBuilder()
                    .header(ApiInterface.AUTH,"a33784c1766647f680078df7cf6b23c0 ")
                    .method(original.method, original.body)
                    .build()
                it.proceed(request)
            }
            // Http Logger
                .addNetworkInterceptor(
                HttpLoggingInterceptor(
                    object : HttpLoggingInterceptor.Logger {
                        override fun log(message: String) {
                            Timber.i(": $message")
                        }
                    }
                ).apply { level = HttpLoggingInterceptor.Level.BODY }
            )
        }.build()
    }
}

