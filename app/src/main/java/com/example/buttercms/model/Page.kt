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
    val page_type: String,
    val fields: Field
) : Parcelable

@Parcelize
data class Field(
    val title: String,
    val content: String,
    val industry: String,
    val subindustry: String,
    val featured_image: String,
    val reviewer: String,
    val study_date: String
) : Parcelable

@Parcelize
data class HomePageResponse(
    val data: List<HomePage>
) : Parcelable

@Parcelize
data class HomePage(
    val fields: HomeField
) : Parcelable

@Parcelize
data class HomeField(
    val headline: String,
    val subheadline: String,
    val section: List<Section>,
    @Json(name = "documentationurl")
    val docUrl: String
) : Parcelable

@Parcelize

data class Section(
    val title: String,
    val subtitle: String,
    val buttonurl: String
) : Parcelable
