package com.example.buttercms.ui.main.page

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.buttercms.error.Callback
import com.buttercms.error.RestCallError
import com.buttercms.getPages
import com.buttercms.model.Pages
import com.example.buttercms.MainActivity.Butter.client
import com.example.buttercms.model.Page
import com.example.buttercms.model.PageResponse

class PageViewModel : ViewModel() {

    private val apiResponsePage by lazy { MutableLiveData<List<Page>>() }
    fun getData(): MutableLiveData<List<Page>> = apiResponsePage

    fun loadData() {
        val queryParameters = HashMap<String, String>()
        queryParameters["locale"] = "en"
        queryParameters["preview"] = "1"

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
                    Log.w("Error", error.errorMessage.toString() + error.errorBody.toString())
                }
            }
        )
    }
}
