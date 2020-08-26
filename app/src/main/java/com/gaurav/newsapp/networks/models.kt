package com.gaurav.newsapp.networks

import android.os.Parcelable
import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
@JsonClass(generateAdapter = true)
data class Article(
    @Json(name = "source") val source: Source? = null,
    @Json(name = "author") val author: String? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "url") val url: String? = null,
    @Json(name = "urlToImage") val urlToImage: String? = null,
    @Json(name = "publishedAt") val publishAt: String? = null,
    @Json(name = "content") val content: String? = null
) : Parcelable

@Keep
@Parcelize
@JsonClass(generateAdapter = true)
data class Source(
    @Json(name = "id") val id: String? = null,
    @Json(name = "name") val name: String? = null
) : Parcelable

@Keep
@Parcelize
@JsonClass(generateAdapter = true)
data class ListResponse(
    @Json(name = "status") val status:String?=null,
    @Json(name="totalResults") val totalResults:Int?=null,
    @Json(name="articles") val articles:List<Article>?=null
):Parcelable
