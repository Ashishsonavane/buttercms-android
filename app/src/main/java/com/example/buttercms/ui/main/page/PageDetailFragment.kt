package com.example.buttercms.ui.main.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.buttercms.databinding.FragmentPageDetailBinding

class PageDetailFragment : Fragment() {
    private val pageViewModel: PageViewModel by viewModels()
    private var _binding: FragmentPageDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPageDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        requireActivity().title = "PageDetail"
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
