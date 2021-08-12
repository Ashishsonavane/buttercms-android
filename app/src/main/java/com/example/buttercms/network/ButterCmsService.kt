package com.example.buttercms.network

import com.example.buttercms.model.BlogResponse
import com.example.buttercms.model.CollectionResponse
import com.example.buttercms.model.PageResponse
import retrofit2.Call
import retrofit2.http.GET

interface ButterCmsInterface {
    companion object {
        private const val AUTH_TOKEN = "3606556ecbd4134ea24b8936a829ab9edaddb583"
    }

    @GET("/v2/posts/?auth_token=$AUTH_TOKEN")
    fun getBlogs(): Call<BlogResponse>

    @GET("/v2/pages/case_studies/?locale=en&preview=1&auth_token=$AUTH_TOKEN")
    fun getPages(): Call<PageResponse>

    @GET("/v2/content/faq/?locale=en&auth_token=$AUTH_TOKEN")
    fun getCollections(): Call<CollectionResponse>
}

object BlogApiCallService {
    private const val BASE_URL = "https://api.buttercms.com/v2/"
    val data = ButterCmsRepository().retrofit(BASE_URL).create(ButterCmsInterface::class.java)
}
