package com.example.buttercms.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

data class BlogResponse(
    @Json(name = "data")
    val blogs: List<Blog>
)

@Parcelize
data class Blog(
    val published: String,
    val title: String,
    @Json(name = "slug")
    val subtitle: String,
    val body: String,
    @Json(name = "featured_image")
    val image: String,
    val author: Author
) : Parcelable

@Parcelize
data class Author(
    @Json(name = "last_name")
    val lastName: String,
    @Json(name = "first_name")
    val firstName: String
) : Parcelable
