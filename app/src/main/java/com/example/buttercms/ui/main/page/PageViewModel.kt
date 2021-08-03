package com.example.buttercms.ui.main.page

import androidx.lifecycle.ViewModel
import com.example.buttercms.model.Pages

class PageViewModel : ViewModel() {

    var pagesList: MutableList<Pages> = ArrayList()

    fun fetchData(): List<Pages> {
        pagesList.add(Pages("Orly Knopp", "EXAMPLE POST", "This is an example blog post", "07/01/2021"))
        pagesList.add(Pages("Orly Knopp", "EXAMPLE POST", "This is an example blog post", "07/01/2021"))
        pagesList.add(Pages("Orly Knopp", "EXAMPLE POST", "This is an example blog post", "07/01/2021"))
        pagesList.add(Pages("Orly Knopp", "EXAMPLE POST", "This is an example blog post", "07/01/2021"))
        pagesList.add(Pages("Orly Knopp", "EXAMPLE POST", "This is an example blog post", "07/01/2021"))
        pagesList.add(Pages("Orly Knopp", "EXAMPLE POST", "This is an example blog post", "07/01/2021"))
        return pagesList
    }
}