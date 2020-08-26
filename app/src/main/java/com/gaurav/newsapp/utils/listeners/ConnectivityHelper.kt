package com.gaurav.newsapp.utils.listeners

import splitties.systemservices.connectivityManager

fun isConnected(): Boolean {
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo?.isConnected ?: false
}