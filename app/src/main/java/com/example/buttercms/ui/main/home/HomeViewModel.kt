package com.example.buttercms.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.buttercms.model.HomePage
import com.example.buttercms.network.BlogApiCallService
import retrofit2.await

class HomeViewModel : ViewModel() {
    private val apiResponseHomePage by lazy { MutableLiveData<List<HomePage>>() }

    fun getData(): MutableLiveData<List<HomePage>> = apiResponseHomePage

    suspend fun loadData() {
        val getCollectionList = BlogApiCallService.data.getHomePage()
        val listResult = getCollectionList.await()
        apiResponseHomePage.postValue(listResult.data)
    }
}
