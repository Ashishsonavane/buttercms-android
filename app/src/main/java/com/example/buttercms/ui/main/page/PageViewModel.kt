package com.example.buttercms.ui.main.page

import androidx.lifecycle.ViewModel
import com.example.buttercms.model.Page

class PageViewModel : ViewModel() {

    var pageList: MutableList<Page> = ArrayList()

    fun fetchData(): List<Page> {
        pageList.add(
            Page(
                "Orly Knopp",
                "EXAMPLE POST",
                "This is an example blog post",
                "07/01/2021"
            )
        )
        pageList.add(
            Page(
                "Orly Knopp",
                "EXAMPLE POST",
                "This is an example blog post",
                "07/01/2021"
            )
        )
        pageList.add(
            Page(
                "Orly Knopp",
                "EXAMPLE POST",
                "This is an example blog post",
                "07/01/2021"
            )
        )
        pageList.add(
            Page(
                "Orly Knopp",
                "EXAMPLE POST",
                "This is an example blog post",
                "07/01/2021"
            )
        )
        pageList.add(
            Page(
                "Orly Knopp",
                "EXAMPLE POST",
                "This is an example blog post",
                "07/01/2021"
            )
        )
        pageList.add(
            Page(
                "Orly Knopp",
                "EXAMPLE POST",
                "This is an example blog post",
                "07/01/2021"
            )
        )
        return pageList
    }
}
