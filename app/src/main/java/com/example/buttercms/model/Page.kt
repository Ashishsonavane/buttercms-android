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
