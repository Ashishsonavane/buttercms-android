package com.example.buttercms.ui.main.page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buttercms.databinding.FragmentPagesBinding

class PageFragment : Fragment() {

    private val pageViewModel: PageViewModel by viewModels()
    private var _binding: FragmentPagesBinding? = null
    private lateinit var pageAdapter: PageAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPagesBinding.inflate(inflater, container, false)
        val view = binding.root

        // load data to the list
        pageAdapter = PageAdapter(pageViewModel.fetchData())
        val recyclerView = binding.rvPages
        val layoutManager: RecyclerView.LayoutManager
        layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = pageAdapter
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}