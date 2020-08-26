package com.gaurav.newsapp.core.data

sealed class Resource<out ResultType : Any?> {

    data class Success<out ResultType : Any?>(val data: ResultType?) : Resource<ResultType>()

    data class Error(
        val errorBody: String? = null,
        val exception: Exception? = null
    ) : Resource<Nothing>() {
        inline fun <reified E> getError() = errorBody
    }
}


