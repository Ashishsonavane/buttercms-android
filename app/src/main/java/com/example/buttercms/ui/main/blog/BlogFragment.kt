package com.example.buttercms.ui.main.blog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buttercms.databinding.FragmentBlogsBinding
import com.example.buttercms.model.Blog

class BlogFragment : Fragment() {

    private val blogViewModel: BlogViewModel by viewModels()
    private var _binding: FragmentBlogsBinding? = null
    private lateinit var blogAdapter: BlogAdapter
    private var blogList = emptyList<Blog>()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBlogsBinding.inflate(inflater, container, false)
        val view = binding.root
        // load data to the list

//        blogList.add(Blog("Orly Knopp", "EXAMPLE POST", "This is an example blog post", "07/01/2021"))
//        blogList.add(Blog("Orly Knopp", "EXAMPLE POST", "This is an example blog post", "07/01/2021"))
//        blogList.add(Blog("Orly Knopp", "EXAMPLE POST", "This is an example blog post", "07/01/2021"))
//        blogList.add(Blog("Orly Knopp", "EXAMPLE POST", "This is an example blog post", "07/01/2021"))
//        blogList.add(Blog("Orly Knopp", "EXAMPLE POST", "This is an example blog post", "07/01/2021"))
//        blogList.add(Blog("Orly Knopp", "EXAMPLE POST", "This is an example blog post", "07/01/2021"))

        blogAdapter = BlogAdapter(blogViewModel.fetchData())
        val recyclerView = binding.rvBlogs
        val layoutManager: RecyclerView.LayoutManager
        layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = blogAdapter
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
