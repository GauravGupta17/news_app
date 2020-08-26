package com.gaurav.newsapp.networks


import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapters.EnumJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object MoshiBuilder {
    fun create() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}
