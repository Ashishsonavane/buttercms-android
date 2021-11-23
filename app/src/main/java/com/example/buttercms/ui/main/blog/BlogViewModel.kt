package com.example.buttercms.ui.main.blog

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.buttercms.error.Callback
import com.buttercms.error.RestCallError
import com.buttercms.getPosts
import com.buttercms.model.Data
import com.buttercms.model.Posts
import com.example.buttercms.MainActivity.Butter.client

class BlogViewModel : ViewModel() {

    private val apiResponseBlog by lazy { MutableLiveData<List<Data>>() }

    fun getData(): MutableLiveData<List<Data>> = apiResponseBlog

    fun loadData() {
        val queryParameters = HashMap<String, String>()
        queryParameters["locale"] = "en"
        queryParameters["preview"] = "1"
        client.data.getPosts(
            emptyMap(),
            callback = object : Callback<Posts, RestCallError> {
                override fun success(response: Posts) {
                    apiResponseBlog.postValue(response.data)
                }
                override fun failure(error: RestCallError) {
                    Log.w("Error", error.errorMessage.toString() + error.errorBody.toString())
                }
            }
        )
    }
}
