package com.example.buttercms.ui.main.blog

import androidx.lifecycle.ViewModel
import com.example.buttercms.model.Blog

class BlogViewModel : ViewModel() {

    var blogList: MutableList<Blog> = ArrayList()

    fun fetchData(): List<Blog> {
        blogList.add(
            Blog(
                "Orly Knopp",
                "EXAMPLE POST",
                "This is an example blog post",
                "07/01/2021"
            )
        )
        blogList.add(
            Blog(
                "Orly Knopp",
                "EXAMPLE POST",
                "This is an example blog post",
                "07/01/2021"
            )
        )
        blogList.add(
            Blog(
                "Orly Knopp",
                "EXAMPLE POST",
                "This is an example blog post",
                "07/01/2021"
            )
        )
        blogList.add(
            Blog(
                "Orly Knopp",
                "EXAMPLE POST",
                "This is an example blog post",
                "07/01/2021"
            )
        )
        blogList.add(
            Blog(
                "Orly Knopp",
                "EXAMPLE POST",
                "This is an example blog post",
                "07/01/2021"
            )
        )
        blogList.add(
            Blog(
                "Orly Knopp",
                "EXAMPLE POST",
                "This is an example blog post",
                "07/01/2021"
            )
        )
        return blogList
    }
}
