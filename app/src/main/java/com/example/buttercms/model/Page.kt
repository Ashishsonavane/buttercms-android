package com.example.buttercms.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

data class PageResponse(
    @Json(name = "data")
    val pages: List<Page>
)

@Parcelize
data class Page(
    @Json(name = "slug")
    val subtitle: String,
    val name: String,
    val published: String?,
    val updated: String?,
    val page_type: String?,
    val fields: Field?
) : Parcelable

@Parcelize
data class Field(
    val readme: String?
) : Parcelable
