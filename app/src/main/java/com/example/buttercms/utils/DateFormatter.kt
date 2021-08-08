package com.example.buttercms.utils

import android.icu.text.SimpleDateFormat
import java.text.ParseException
import java.util.*

class DateFormatter {
    fun formatDate(oldDate: String?): String? {

        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.timeZone = TimeZone.getTimeZone("UTC")
        try {
            calendar.time = sdf.parse(oldDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val output = SimpleDateFormat("MMM dd yyyy", Locale.getDefault())
        return output.format(calendar.time)
    }
}
