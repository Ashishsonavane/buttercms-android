package com.example.buttercms.ui.main.page

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.buttercms.model.Page
import com.example.buttercms.network.BlogApiCallService
import retrofit2.await

class PageViewModel : ViewModel() {

    private val apiResponsePage by lazy { MutableLiveData<List<Page>>() }

    fun getData(): MutableLiveData<List<Page>> = apiResponsePage

    suspend fun loadData() {
        val getPages = BlogApiCallService.blogs.getPages()
        val listResult = getPages.await()
        apiResponsePage.postValue(listResult.pages)
    }
}
