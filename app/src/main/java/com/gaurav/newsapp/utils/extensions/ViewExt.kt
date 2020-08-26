package com.gaurav.newsapp.utils.extensions

import android.content.Context
import android.view.View
import androidx.annotation.StringRes

const val FLAGS_FULLSCREEN =
    View.SYSTEM_UI_FLAG_LOW_PROFILE or
            View.SYSTEM_UI_FLAG_FULLSCREEN or
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeInvisible(makeGone: Boolean = false) {
    visibility = if (makeGone) View.GONE else View.INVISIBLE
}



fun Context?.toast(
    msg: String? = null, @StringRes msgRes: Int? = null,
    duration: Int = android.widget.Toast.LENGTH_SHORT
) {
    msgRes?.let {
        android.widget.Toast.makeText(this, it, duration).show()
    } ?: {
        android.widget.Toast.makeText(this, msg, duration).show()
    }.invoke()
}

