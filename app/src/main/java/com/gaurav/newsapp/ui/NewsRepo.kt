package com.gaurav.newsapp.ui

import com.gaurav.newsapp.core.network.BaseRepo
import com.gaurav.newsapp.networks.ApiInterface

class NewsRepo(private val apiInterface: ApiInterface) : BaseRepo() {

    suspend fun getHeadLines() = loadData { apiInterface.getHeadlines() }

}