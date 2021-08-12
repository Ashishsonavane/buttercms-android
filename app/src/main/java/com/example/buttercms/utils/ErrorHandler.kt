package com.example.buttercms.utils

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.CoroutineExceptionHandler

fun createCoroutineErrorHandler(context: Context) = CoroutineExceptionHandler { _, exception ->
    Toast.makeText(context, exception.message, Toast.LENGTH_SHORT).show()
}
