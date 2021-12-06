package com.example.buttercms.ui.main.page

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.buttercms.error.Callback
import com.buttercms.error.RestCallError
import com.buttercms.getPages
import com.buttercms.model.Pages
import com.example.buttercms.MainActivity.Butter.client
import com.example.buttercms.model.Page
import com.example.buttercms.model.PageResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PageViewModel : ViewModel() {

    private val apiResponsePage by lazy { MutableLiveData<List<Page>>() }
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    val errorMessage = MutableLiveData<String>()

    fun getData(): MutableLiveData<List<Page>> = apiResponsePage

    fun loadData() {
        val queryParameters = HashMap<String, String>()
        queryParameters["locale"] = "en"
        queryParameters["preview"] = "1"
        coroutineScope.launch {
            client.data.getPages(
                "case_studies",
                queryParameters,
                Page::class.java,
                callback = object : Callback<Pages, RestCallError> {
                    override fun success(response: Pages) {
                        // mapping to my data class
                        fun Pages.toMyPageResponse() = PageResponse(
                            data = data as List<Page>
                        )

                        val newResponse = response.toMyPageResponse()
                        apiResponsePage.postValue(newResponse.data)
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
