package com.example.buttercms.utils

import android.icu.text.SimpleDateFormat
import android.util.Log
import java.text.ParseException
import java.util.*

class DateFormatter {
    fun formatDate(oldDate: String?): String? {

        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.US)
        val calendar = Calendar.getInstance()
        calendar.timeZone = TimeZone.getTimeZone("UTC")
        try {
            calendar.time = sdf.parse(oldDate)
        } catch (e: ParseException) {
            Log.e("Error here$oldDate", e.toString())
        }
        val output = SimpleDateFormat("MMM dd yyyy", Locale.US)
        return output.format(calendar.time)
    }
}
