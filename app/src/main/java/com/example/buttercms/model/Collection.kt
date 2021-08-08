package com.example.buttercms.model

import com.squareup.moshi.Json

data class CollectionResponse(
    val data: Data
)

data class Data(
    @Json(name = "test")
    val collection: List<Collection>
)

data class Collection(
    val name: String,
    val description: String
)
