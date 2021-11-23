package com.example.buttercms.model

import com.buttercms.model.Meta

data class CollectionResponse(
    val meta: Meta?,
    val data: CollectionData
)

data class Meta(
    val next_page: Int?,
    val previous_page: Int?,
    val count: Int
)

data class CollectionData(
    val faq: List<Collection>
)

data class Collection(
    val meta: MetaItem,
    val question: String,
    val answer: String
)

data class MetaItem(
    val id: Int
)
