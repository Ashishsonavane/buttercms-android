package com.example.buttercms.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.buttercms.error.Callback
import com.buttercms.error.RestCallError
import com.buttercms.getPage
import com.buttercms.model.Page
import com.example.buttercms.MainActivity.Butter.client
import com.example.buttercms.model.HomePage
import com.example.buttercms.model.HomePageResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val apiResponseHomePage by lazy { MutableLiveData<HomePage>() }

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    val errorMessage = MutableLiveData<String>()

    fun getData(): MutableLiveData<HomePage> = apiResponseHomePage

    fun loadData() {
        val queryParameters = HashMap<String, String>()
        queryParameters["locale"] = "en"
        queryParameters["preview"] = "1"
        coroutineScope.launch {
            client.data.getPage(
                "homepage",
                "homepage",
                queryParameters,
                HomePage::class.java,
                callback = object : Callback<Page, RestCallError> {
                    override fun success(response: Page) {
                        // mapping to my data class
                        fun Page.toHomePageResponse() = HomePageResponse(
                            data = data as HomePage
                        )

                        val newResponse = response.toHomePageResponse()
                        apiResponseHomePage.postValue(newResponse.data)
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
