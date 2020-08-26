package com.gaurav.newsapp.core.network

import androidx.lifecycle.MutableLiveData
import com.gaurav.newsapp.utils.extensions.asResource
import com.gaurav.khalilabadapp.core.NoConnectionException
import com.gaurav.newsapp.core.data.Resource
import com.gaurav.newsapp.utils.listeners.isConnected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber
import java.net.ConnectException
import java.net.UnknownHostException


typealias NetworkCall<R> = suspend () -> Response<R>

open class BaseRepo {
    suspend inline fun <R : Any> loadData(
        noinline call: NetworkCall<R>
    ): MutableLiveData<Resource<R>> = coroutineScope {

        val result = MutableLiveData<Resource<R>>()
        Timber.i("loadData: result: $result")

        // Load from network

        val res = performNetworkCall<R>(call)
        Timber.d("loadData: res: $res")

        withContext(Dispatchers.Main) { result.value = res }

        Timber.d("loadData: return result: ${result.value}")
        return@coroutineScope result
    }

    suspend inline fun <R : Any> performNetworkCall(
        crossinline call: NetworkCall<R>
    ): Resource<R> = coroutineScope {

        Timber.i("performNetworkCall() called with: call")

        var result: Resource<R>
        try {
            if (isConnected()) {
                val response = call.invoke()
                Timber.i("performNetworkCall: ${response.body()}")
                result = response.asResource()

                /* if (response.isSuccessful && body != null) {
                     result = Resource.Success(body)
                 } else {
                     result = Resource.Error(response.errorBody()?.getError<E>())
                 }*/
            } else {
                result = Resource.Error(exception = NoConnectionException())
            }
        } catch (e: UnknownHostException) {
            e.printStackTrace()
            result = Resource.Error(exception = NoConnectionException())
        } catch (e: ConnectException) {
            e.printStackTrace()
            result = Resource.Error(exception = NoConnectionException())
        } catch (e: Exception) {
            e.printStackTrace()
            result = Resource.Error(exception = e)
        }

        Timber.d("performNetworkCall() result: $result")
        return@coroutineScope result
    }
}