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
        val getCollectionList = BlogApiCallService.data.getCollections()
        val listResult = getCollectionList.await()
        apiResponseCollection.postValue(listResult.data.collection)
    }
}
