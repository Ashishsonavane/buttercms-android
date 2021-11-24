package com.example.buttercms.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

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
