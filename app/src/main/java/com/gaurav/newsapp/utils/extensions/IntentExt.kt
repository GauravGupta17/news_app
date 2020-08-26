package com.gaurav.newsapp.utils.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

inline fun <reified T : Any> Context.intentFor(vararg params: Pair<String, Any?>) =
    Intent(this, T::class.java).apply {
        putExtras(
            bundleOf(*params)
        )
    }

inline fun <reified T : Activity> Context.startActivity(vararg params: Pair<String, Any?>) {
    startActivity(intentFor<T>(*params))
}


fun Intent.clearTask(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) }

fun Intent.newTask(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }