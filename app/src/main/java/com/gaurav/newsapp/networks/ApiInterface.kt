package com.gaurav.newsapp.networks

import co.zoozle.android.zoozle.data.networks.*
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface ApiInterface {

    @GET("top-headlines/")
    suspend fun getHeadlines(@Query("country") country: String = "in"): Response<ListResponse>

    companion object {
        //newsapi.org
        private const val BASE_URL = "https://newsapi.org/v2/"
        const val AUTH = "Authorization"
        fun create(okHttpClient: OkHttpClient, moshi: Moshi): ApiInterface {

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(ApiInterface::class.java)
        }
    }
}