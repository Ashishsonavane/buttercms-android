package com.example.buttercms.ui.main.blog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.buttercms.error.Callback
import com.buttercms.error.RestCallError
import com.buttercms.getPosts
import com.buttercms.model.Data
import com.buttercms.model.Posts
import com.example.buttercms.MainActivity.Butter.client
import kotlinx.coroutines.*

class BlogViewModel : ViewModel() {

    private val apiResponseBlog by lazy { MutableLiveData<List<Data>>() }
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    val errorMessage = MutableLiveData<String>()

    fun getData(): MutableLiveData<List<Data>> = apiResponseBlog

    fun loadData() {
        val queryParameters = HashMap<String, String>()
        queryParameters["locale"] = "en"
        queryParameters["preview"] = "1"
        coroutineScope.launch {
            client.data.getPosts(
                emptyMap(),
                callback = object : Callback<Posts, RestCallError> {
                    override fun success(response: Posts) {
                        apiResponseBlog.postValue(response.data)
                    }

                    override fun failure(error: RestCallError) {
                        onError("Error: ${error.errorMessage + " " + error.errorBody}")
                    }
                }
            )
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
