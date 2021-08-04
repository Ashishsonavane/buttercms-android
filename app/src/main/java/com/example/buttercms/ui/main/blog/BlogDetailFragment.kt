package com.example.buttercms.ui.main.blog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.buttercms.databinding.FragmentBlogDetailBinding

class BlogDetailFragment : Fragment() {
    private val blogViewModel: BlogViewModel by viewModels()
    private var _binding: FragmentBlogDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBlogDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        requireActivity().title = "BlogDetail"
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
