package com.example.buttercms.ui.main.collection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.buttercms.error.Callback
import com.buttercms.error.RestCallError
import com.buttercms.getCollection
import com.buttercms.model.Collections
import com.example.buttercms.MainActivity.Butter.client
import com.example.buttercms.model.*
import com.example.buttercms.model.Collection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CollectionViewModel : ViewModel() {

    private val apiResponseCollection by lazy { MutableLiveData<List<Collection>>() }
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    val errorMessage = MutableLiveData<String>()

    fun getData(): MutableLiveData<List<Collection>> = apiResponseCollection

    fun loadData() {
        val queryParameters = HashMap<String, String>()
        queryParameters["locale"] = "en"
        queryParameters["preview"] = "1"
        coroutineScope.launch {
            client.data.getCollection(
                "faq",
                queryParameters,
                CollectionData::class.java,
                callback = object : Callback<Collections, RestCallError> {
                    override fun success(response: Collections) {
                        // mapping to my data class
                        fun Collections.toMyCollections() = CollectionResponse(
                            data = data as CollectionData,
                            meta = meta
                        )

                        val newResponse = response.toMyCollections()
                        apiResponseCollection.postValue(newResponse.data.faq)
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
