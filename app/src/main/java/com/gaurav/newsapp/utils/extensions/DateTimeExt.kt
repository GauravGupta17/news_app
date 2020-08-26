package com.gaurav.khalilabadapp.utils.extensions

import android.annotation.SuppressLint
import com.gaurav.khalilabadapp.utils.extensions.DateTimeFormats.SDF_COMPACT_DISPLAY_DAY_MONTH
import com.gaurav.khalilabadapp.utils.extensions.DateTimeFormats.SDF_COMPACT_DISPLAY_TIME_12H
import java.text.SimpleDateFormat
import java.util.*


fun Date.getCompactDisplayTime(): String? = SDF_COMPACT_DISPLAY_TIME_12H.format(this)

fun Date.getCompactDayMonth(): String? = SDF_COMPACT_DISPLAY_DAY_MONTH.format(this)

/* Constants */
@SuppressLint("ConstantLocale")
object DateTimeFormats {
    const val DISPLAY_TIME = "hh:mm a"
    const val DISPLAY_DATE = "dd MMM yyyy"
    val SDF_COMPACT_DISPLAY_TIME_12H = SimpleDateFormat("h:mm a", Locale.getDefault())
    val SDF_COMPACT_DISPLAY_DAY_MONTH = SimpleDateFormat("dd MMM", Locale.getDefault())
    val SDF_DISPLAY_TIME = SimpleDateFormat(DISPLAY_TIME, Locale.getDefault())
    val SDF_DISPLAY_DATE = SimpleDateFormat(DISPLAY_DATE, Locale.getDefault())
}

fun String.getDate():String{
    val dateArray = this.split(" ")
    return getDateFromatter(dateArray[0])
}
fun getDateFromatter(date: String): String {
    val dateArray = date.split("-")
    val month = getMonth(dateArray[1].toInt())
    return "${getDatePS(dateArray[2])} $month, ${dateArray[0]}"
}

private fun getMonth(month: Int): String {
    return SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).dateFormatSymbols.shortMonths[month-1]
}

private fun getDatePS(date: String): String {
    return when (date) {
        "01" -> "1st"
        "02" -> "2nd"
        "03" -> "3rd"
        "21" -> "21st"
        "22" -> "22nd"
        "23" -> "23rd"
        "31" -> "31st"
        else -> "${date}th"
    }
}
fun String.getDateorTime():String{
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
    val createdTime = dateFormat.parse(this)
    val createdDate = Calendar.getInstance(Locale.ENGLISH).also { it.time = createdTime }
    val currentDate =Calendar.getInstance(Locale.ENGLISH).also { it.time = Date() }
    return if (currentDate.get(Calendar.DATE).equals(createdDate.get(Calendar.DATE)))
        "${createdTime.getCompactDisplayTime()}"
    else
        "${createdTime.getCompactDayMonth()}"
}





