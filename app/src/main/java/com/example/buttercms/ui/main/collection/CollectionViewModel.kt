package com.example.buttercms.ui.main.collection

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.buttercms.error.Callback
import com.buttercms.error.RestCallError
import com.buttercms.getCollection
import com.buttercms.model.Collections
import com.example.buttercms.MainActivity.Butter.client
import com.example.buttercms.model.*
import com.example.buttercms.model.Collection

class CollectionViewModel : ViewModel() {

    private val apiResponseCollection by lazy { MutableLiveData<List<Collection>>() }

    fun getData(): MutableLiveData<List<Collection>> = apiResponseCollection

    fun loadData() {
        val queryParameters = HashMap<String, String>()
        queryParameters["locale"] = "en"
        queryParameters["preview"] = "1"

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
                    Log.w("Error", error.errorMessage.toString() + error.errorBody.toString())
                }
            }
        )
    }
}
