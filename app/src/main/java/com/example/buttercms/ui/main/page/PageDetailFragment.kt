package com.example.buttercms.ui.main.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.buttercms.databinding.FragmentPageDetailBinding
import java.util.*

class PageDetailFragment : Fragment() {
    private val args: PageDetailFragmentArgs by navArgs()
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

        val input = args.page.published

        binding.tvTitlePageDetail.text = args.page.name
        binding.tvSubtitlePageDetail.text = args.page.subtitle
        binding.tvTimePageDetail.text = args.time

        binding.wVPostDetail.apply {
            args.page.fields?.readme?.let { loadData(it, "text/html; charset=UTF-8", "null") }
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
//            setInitialScale(1)
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = false
//            settings.mixedContentMode = WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE
//            settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
            isVerticalScrollBarEnabled = false
            isHorizontalScrollBarEnabled = false
        }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
