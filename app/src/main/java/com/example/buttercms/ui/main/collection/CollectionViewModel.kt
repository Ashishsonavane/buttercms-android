package com.example.buttercms.ui.main.collection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.buttercms.model.Collection
import com.example.buttercms.network.BlogApiCallService
import retrofit2.await

class CollectionViewModel : ViewModel() {

    private val apiResponseCollection by lazy { MutableLiveData<List<Collection>>() }

    fun getData(): MutableLiveData<List<Collection>> = apiResponseCollection

    suspend fun loadData() {
        val getCollections = BlogApiCallService.blogs.getCollections()
        val listResult = getCollections.await()
        apiResponseCollection.postValue(listResult.data.collection)
    }
}
