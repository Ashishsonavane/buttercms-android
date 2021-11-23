package com.example.buttercms.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

data class PageResponse(
    val data: List<Page>
)

@Parcelize
data class Page(
    val slug: String,
    val name: String,
    val published: Date?,
    val updated: Date?,
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
    val data: HomePage
) : Parcelable

@Parcelize
data class HomePage(
    val slug: String?,
    val name: String?,
    val published: Date?,
    val updated: Date?,
    val page_type: String?,
    val fields: HomeField?
) : Parcelable

@Parcelize
data class HomeField(
    val headline: String,
    val subheadline: String?,
    val section: List<Section>,
    val documentationurl: String?
) : Parcelable

@Parcelize
data class Section(
    val title: String,
    val subtitle: String,
    val buttonurl: String
) : Parcelable
